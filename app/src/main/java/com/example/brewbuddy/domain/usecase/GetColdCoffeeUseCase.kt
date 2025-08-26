package com.example.brewbuddy.domain.usecase

import com.example.brewbuddy.domain.repo.CoffeeRepo
import javax.inject.Inject

class GetColdCoffeeUseCase @Inject constructor(private var coffeeRepository: CoffeeRepo) {
    suspend operator fun invoke() = coffeeRepository.getColdCoffee()
}