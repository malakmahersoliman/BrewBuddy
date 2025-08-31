package com.example.brewbuddy.presentation.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewbuddy.data.orderdb.OrderEntity
import com.example.brewbuddy.domain.model.Order
import com.example.brewbuddy.domain.usecase.AddOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val addOrderUseCase: AddOrderUseCase
) : ViewModel() {

    fun addOrder(order: Order) {
        viewModelScope.launch {
            addOrderUseCase(order)
        }
    }
}
