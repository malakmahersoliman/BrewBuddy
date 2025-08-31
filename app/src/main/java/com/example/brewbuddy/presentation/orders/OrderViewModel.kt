package com.example.brewbuddy.presentation.orders

import androidx.lifecycle.ViewModel
import com.example.brewbuddy.domain.model.Order
import com.example.brewbuddy.domain.usecase.AddOrderUseCase
import com.example.brewbuddy.domain.usecase.GetOrdersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@HiltViewModel
class OrderViewModel @Inject constructor(

    private val getOrdersUseCase: GetOrdersUseCase
) : ViewModel(){

    private val _allOrders = MutableStateFlow<List<Order>>(emptyList())

    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()



    init {
        getAllOrders()
    }

    private fun getAllOrders() {
        viewModelScope.launch {
            getOrdersUseCase().collect { ordersList ->
                _allOrders.value = ordersList
                _orders.value = ordersList // default
            }
        }
    }



    fun filterOrders(type: String) {
        viewModelScope.launch {
            getOrdersUseCase().collect { ordersList ->
                val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val today = Date()

                _orders.value = when(type){
                    "recent" -> ordersList.filter { order ->
                        val date = inputFormat.parse(order.date) // order.date = String
                        date != null && !date.before(today)
                    }
                    "past" -> ordersList.filter { order ->
                        val date = inputFormat.parse(order.date)
                        date != null && date.before(today)
                    }
                    else -> ordersList
                }
            }
        }
    }

}