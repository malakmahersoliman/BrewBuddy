package com.example.brewbuddy.domain.usecase

import com.example.brewbuddy.domain.model.Coffee
import com.example.brewbuddy.domain.repository.CoffeeRepository
import javax.inject.Inject


class GetRecommendationsUseCase @Inject constructor(
    private val repository: CoffeeRepository
) {
    suspend operator fun invoke(count: Int = 6): List<Coffee> {
        val hotDrinks = repository.getHotCoffee()
        val coldDrinks = repository.getColdCoffee()

        // Combine and shuffle both lists, then take specified count
        val allDrinks = (hotDrinks + coldDrinks).shuffled()
        return allDrinks.take(count)
    }
}
