package com.example.brewbuddy.presentation.drinkDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewbuddy.domain.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkDetailsViewModel @Inject constructor(
    private val repo: FavoritesRepository
) : ViewModel() {

    private val _quantity = MutableLiveData(1)
    val quantity: LiveData<Int> = _quantity

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    private var currentDrinkId: Int = -1

    fun checkFavoriteStatus(drinkId: Int) {
        currentDrinkId = drinkId
        viewModelScope.launch {
            _isFavorite.value = repo.isFavorite(drinkId)
        }
    }

    fun increaseQuantity() {
        val current = _quantity.value ?: 1
        _quantity.value = current + 1
    }

    fun decreaseQuantity() {
        val current = _quantity.value ?: 1
        if (current > 1) {
            _quantity.value = current - 1
        }
    }
    fun toggleFavorite() {
        viewModelScope.launch {
            if (_isFavorite.value == true) {
                repo.removeById(currentDrinkId)
                _isFavorite.value = false
            } else {
                // You'll need to pass the drink data to add to favorites
                _isFavorite.value = true
            }
        }
    }

}