package com.example.brewbuddy.domain.usecase

import com.example.brewbuddy.data.entities.Coffee
import com.example.brewbuddy.domain.repo.CoffeeRepo
import javax.inject.Inject

class GetHotCoffeeUseCase @Inject constructor(
    private val repository: CoffeeRepo
) {
    suspend operator fun invoke(): List<Coffee> = repository.getHotCoffee()
}