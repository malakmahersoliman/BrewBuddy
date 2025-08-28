package com.example.brewbuddy.presentation.common.format

import java.text.NumberFormat
import java.util.Locale

object Formatters {
    private val currency by lazy { NumberFormat.getCurrencyInstance(Locale.getDefault()) }
    fun formatPrice(value: Double): String = currency.format(value)
}
