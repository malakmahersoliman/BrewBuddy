package com.example.brewbuddy.data.local

import com.example.brewbuddy.domain.model.Drink

object FakeCatalog {
    val hotDrinks = listOf(
        Drink(
            id = 1,
            title = "Latte",
            description = "Espresso with steamed milk",
            image = "https://upload.wikimedia.org/wikipedia/commons/7/7e/Caff%C3%A8_Latte_at_Sightglass_Coffee.jpg",
            ingredients = listOf("Espresso", "Steamed Milk"),
            price = 4.50
        ),
        Drink(
            id = 2,
            title = "Cappuccino",
            description = "Espresso with steamed milk foam",
            image = "https://upload.wikimedia.org/wikipedia/commons/c/c8/Cappuccino_at_Sightglass_Coffee.jpg",
            ingredients = listOf("Espresso", "Steamed Milk", "Foam"),
            price = 4.99
        ),
        Drink(
            id = 3,
            title = "Americano",
            description = "Espresso diluted with hot water",
            image = "https://upload.wikimedia.org/wikipedia/commons/6/6b/Caff%C3%A8_Americano.jpg",
            ingredients = listOf("Espresso", "Water"),
            price = 3.50
        )
    )

    val icedDrinks = listOf(
        Drink(
            id = 4,
            title = "Iced Latte",
            description = "Chilled espresso with cold milk and ice",
            image = "https://upload.wikimedia.org/wikipedia/commons/f/f6/Iced_Coffee_with_Milk.jpg",
            ingredients = listOf("Espresso", "Cold Milk", "Ice"),
            price = 5.00
        ),
        Drink(
            id = 5,
            title = "Iced Americano",
            description = "Espresso with cold water and ice",
            image = "https://upload.wikimedia.org/wikipedia/commons/4/45/Iced_Americano.jpg",
            ingredients = listOf("Espresso", "Cold Water", "Ice"),
            price = 4.20
        )
    )

    // 🟢 دي اللي هتستخدم في getCatalog()
    val allDrinks = hotDrinks + icedDrinks
}
