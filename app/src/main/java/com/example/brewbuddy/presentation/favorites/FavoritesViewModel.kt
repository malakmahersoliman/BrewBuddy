package com.example.brewbuddy.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewbuddy.domain.model.Favorite
import com.example.brewbuddy.domain.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    val favorites: StateFlow<List<Favorite>> =
        favoritesRepository.favorites
            .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun removeFavorite(id: Int) {
        viewModelScope.launch {
            favoritesRepository.removeById(id)
        }
    }
}
