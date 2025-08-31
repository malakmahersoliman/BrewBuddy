package com.example.brewbuddy.presentation.drinkDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewbuddy.data.api.WebServices
import com.example.brewbuddy.domain.model.Drink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkDetailsViewModel @Inject constructor(
    private val apiService: WebServices
) : ViewModel() {

    private val _drink = MutableLiveData<Drink>()
    val drink: LiveData<Drink> = _drink

    private val _quantity = MutableLiveData(1)
    val quantity: LiveData<Int> = _quantity

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
}