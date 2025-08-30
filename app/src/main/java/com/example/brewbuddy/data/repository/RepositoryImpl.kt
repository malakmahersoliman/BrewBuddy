package com.example.brewbuddy.data.repository

import com.example.brewbuddy.data.api.ApiManager
import com.example.brewbuddy.domain.model.Coffee
import com.example.brewbuddy.data.mappers.toCoffee
import com.example.brewbuddy.domain.repo.CoffeeRepo
import javax.inject.Inject

class CoffeeRepoImpl @Inject constructor(
    private val api: ApiManager ) : CoffeeRepo {
    override suspend fun getColdCoffee(): List<Coffee> {
        val dtoList = api.getWebServices().getIcedCoffee()
        return dtoList.map { it.toCoffee() }
    }

    override suspend fun getHotCoffee(): List<Coffee> {
        val dtoList = api.getWebServices().getHotCoffee()
        return dtoList.map { it.toCoffee() }
    }

}