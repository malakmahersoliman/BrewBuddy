package com.example.brewbuddy.domain.repo

interface UserRepo {
    fun saveName(name: String)
    fun getName(): String?
    fun hasName(): Boolean
}
