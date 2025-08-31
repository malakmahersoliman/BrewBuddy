package com.example.brewbuddy.presentation.drinkDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewbuddy.data.api.WebServices
import com.example.brewbuddy.domain.model.Drink
import com.example.brewbuddy.domain.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkDetailsViewModel @Inject constructor(
    private val apiService: WebServices,
    private val repo: FavoritesRepository
) : ViewModel() {

    private val _drink = MutableLiveData<Drink>()
    val drink: LiveData<Drink> = _drink

    private val _quantity = MutableLiveData(1)
    val quantity: LiveData<Int> = _quantity
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun loadDrink(drinkId: Int) {
        viewModelScope.launch {
            try {
                val result = apiService.getDrinkById(drinkId)
                _drink.value = result
            } catch (e: Exception) {
                // TODO: handle error (e.g., add _error LiveData if needed)
            }
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
        val currentDrink = _drink.value ?: return
        viewModelScope.launch {
            if (_isFavorite.value == true) {
                repo.removeById(currentDrink.id)
                _isFavorite.value = false
            } else {
                repo.add(currentDrink)
                _isFavorite.value = true
            }
        }
    }

}