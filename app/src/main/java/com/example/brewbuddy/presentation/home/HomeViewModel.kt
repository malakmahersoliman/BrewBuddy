package com.example.brewbuddy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewbuddy.domain.model.Drink
import com.example.brewbuddy.domain.repository.CatalogRepository
import com.example.brewbuddy.domain.repository.FavoritesRepository
import com.example.brewbuddy.domain.usecase.GetUserNameUseCase   // <-- add
import com.example.brewbuddy.presentation.common.adapter.UiDrink
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val catalogRepository: CatalogRepository,
    private val favoritesRepository: FavoritesRepository,
    private val getUserName: GetUserNameUseCase                  // <-- add
) : ViewModel() {

    // Greeting (uses your SharedPrefs-backed use case)
    private val _greeting = MutableStateFlow("Hi, —")
    val greeting: StateFlow<String> = _greeting

    init {
        val name = getUserName() ?: "Friend"
        _greeting.value = "Hi, $name 👋"
    }

    // Existing items stream (unchanged)
    val items: StateFlow<List<UiDrink>> =
        combine(
            catalogRepository.getCatalog(),
            favoritesRepository.favorites     // Flow<List<Favorite>>
        ) { catalog: List<Drink>, favorites ->
            val favIds = favorites.map { it.id }.toSet()
            catalog.map { drink -> UiDrink(drink, favIds.contains(drink.id)) }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun onToggleFavorite(drink: Drink, shouldFavorite: Boolean) {
        viewModelScope.launch {
            if (shouldFavorite) favoritesRepository.add(drink)
            else favoritesRepository.removeById(drink.id)
        }
    }
}
