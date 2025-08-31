package com.example.brewbuddy.data.api


import com.example.brewbuddy.data.entities.CoffeeModel
import com.example.brewbuddy.domain.model.Coffee
import com.example.brewbuddy.domain.model.Drink
import retrofit2.http.GET
import retrofit2.http.Path

interface WebServices {
    @GET("coffee/hot")
    suspend fun getHotCoffee(): List<CoffeeModel.CoffeeModelItem>

    @GET("coffee/iced")
    suspend fun getIcedCoffee(): List<CoffeeModel.CoffeeModelItem>

    @GET("drinks/{id}")
    suspend fun getDrinkById(@Path("id") id: Int): Drink
}