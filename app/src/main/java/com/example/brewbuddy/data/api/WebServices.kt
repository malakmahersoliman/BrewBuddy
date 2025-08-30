package com.example.brewbuddy.data.api


import com.example.brewbuddy.data.entities.CoffeeModel
import com.example.brewbuddy.domain.model.Coffee
import retrofit2.http.GET

interface WebServices {
    @GET("coffee/hot")
    suspend fun getHotCoffee(): List<CoffeeModel.CoffeeModelItem>

    @GET("coffee/iced")
    suspend fun getIcedCoffee(): List<CoffeeModel.CoffeeModelItem>
}