package com.example.brewbuddy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewbuddy.domain.model.Drink
import com.example.brewbuddy.domain.repository.CatalogRepository
import com.example.brewbuddy.domain.repository.FavoritesRepository
import com.example.brewbuddy.presentation.common.adapter.UiDrink
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val catalogRepository: CatalogRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    val items: StateFlow<List<UiDrink>> =
        combine(
            catalogRepository.getCatalog(),
            favoritesRepository.favorites             // Flow<List<Favorite>>
        ) { catalog: List<Drink>, favorites ->
            val favIds = favorites.map { it.id }.toSet()
            catalog.map { drink ->
                UiDrink(drink, favIds.contains(drink.id))
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onToggleFavorite(drink: Drink, shouldFavorite: Boolean) {
        viewModelScope.launch {
            if (shouldFavorite) {
                favoritesRepository.add(drink)
            } else {
                favoritesRepository.removeById(drink.id)
            }
        }
    }
}
