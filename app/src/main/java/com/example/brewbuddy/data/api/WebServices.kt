package com.example.brewbuddy.data.api


import com.example.brewbuddy.data.entities.Coffee
import retrofit2.http.GET

interface WebServices {
    @GET("coffee/hot")
    suspend fun getHotCoffee(): List<Coffee>

    @GET("coffee/iced")
    suspend fun getIcedCoffee(): List<Coffee>
}