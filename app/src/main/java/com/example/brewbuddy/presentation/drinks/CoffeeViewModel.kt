package com.example.brewbuddy.presentation.drinks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewbuddy.domain.model.Coffee
import com.example.brewbuddy.domain.usecase.GetColdCoffeeUseCase
import com.example.brewbuddy.domain.usecase.GetHotCoffeeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeViewModel @Inject constructor(
    private val getHotCoffeeUseCase: GetHotCoffeeUseCase,
    private val getColdCoffeeUseCase: GetColdCoffeeUseCase
) : ViewModel() {

    private val _coldList = MutableLiveData<List<Coffee>>()
    private val _hotList = MutableLiveData<List<Coffee>>()
    private val _coffeeList = MutableLiveData<List<Coffee>>()

    val coffeeList: LiveData<List<Coffee>> = _coffeeList

    private var currentType: CoffeeType = CoffeeType.HOT

    fun fetchCoffee() {
        viewModelScope.launch {
            val hot = getHotCoffeeUseCase().map { it.apply { price = "Rp ${(200..500).random()}" } }
            val cold = getColdCoffeeUseCase().map { it.apply { price = "Rp ${(200..500).random()}" } }

            _hotList.value = hot
            _coldList.value = cold

            _coffeeList.value = _hotList.value
        }
    }

    fun setCoffeeType(type: CoffeeType) {
        currentType = type
        _coffeeList.value = when (type) {
            CoffeeType.HOT -> _hotList.value
            CoffeeType.COLD -> _coldList.value
        }
    }

    fun search(query: String) {
        val baseList = when (currentType) {
            CoffeeType.HOT -> _hotList.value ?: emptyList()
            CoffeeType.COLD -> _coldList.value ?: emptyList()
        }
        _coffeeList.value = if (query.isEmpty()) baseList else baseList.filter { it.title.contains(query, ignoreCase = true) }
    }
    enum class CoffeeType { HOT, COLD }
}