package com.example.brewbuddy.domain.usecase

import com.example.brewbuddy.domain.model.Coffee
import com.example.brewbuddy.domain.repository.CoffeeRepository
import javax.inject.Inject

class GetColdCoffeeUseCase @Inject constructor(
    private val repository: CoffeeRepository
) {
    suspend operator fun invoke(): List<Coffee> = repository.getColdCoffee()
}