package com.example.brewbuddy.ui

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.brewbuddy.domain.usecase.GetColdCoffeeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoffeeViewModel @Inject constructor(private val useCase: GetColdCoffeeUseCase) : ViewModel() {
}