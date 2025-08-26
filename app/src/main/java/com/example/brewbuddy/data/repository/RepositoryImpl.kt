package com.example.brewbuddy.data.repository

import com.example.brewbuddy.data.api.ApiManager
import com.example.brewbuddy.data.entities.Coffee
import com.example.brewbuddy.domain.repo.CoffeeRepo
import javax.inject.Inject

class CoffeeRepoImpl @Inject constructor(
    private val api: ApiManager ) : CoffeeRepo {
    override suspend fun getColdCoffee()= api.getWebServices().getIcedCoffee()

    override suspend fun getHotCoffee() = api.getWebServices().getHotCoffee()

}