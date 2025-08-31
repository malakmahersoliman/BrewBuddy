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
import com.example.brewbuddy.data.db.converters.DateConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
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
                val todayCal = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }
                val today = todayCal.time

                _orders.value = when (type) {
                    "recent" -> ordersList.filter { order ->
                        try {
                            isSameDay(order.date, today)
                        } catch (e: Exception) {
                            false
                        }
                    }
                    "past" -> ordersList.filter { order ->
                        try {
                            order.date.before(today)
                        } catch (e: Exception) {
                            false
                        }
                    }
                    else -> ordersList
                }
            }
        }
    }


    private fun isSameDay(date1: Date, date2: Date): Boolean {
        val cal1 = Calendar.getInstance().apply { time = date1 }
        val cal2 = Calendar.getInstance().apply { time = date2 }
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
    }


}