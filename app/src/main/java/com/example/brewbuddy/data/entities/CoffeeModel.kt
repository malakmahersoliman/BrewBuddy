package com.example.brewbuddy.data.entities

import com.google.gson.annotations.SerializedName

class CoffeeModel : ArrayList<CoffeeModel.CoffeeModelItem>(){
    data class CoffeeModelItem(
        @SerializedName("description")
        var description: String? = "",
        @SerializedName("id")
        var id: Int? = 0,
        @SerializedName("image")
        var image: String? = "",
        @SerializedName("ingredients")
        var ingredients: List<String?>? = listOf(),
        @SerializedName("title")
        var title: String? = ""
    )
}