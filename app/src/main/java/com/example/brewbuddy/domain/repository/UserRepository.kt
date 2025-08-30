package com.example.brewbuddy.domain.repository

interface UserRepository {
    fun saveName(name: String)
    fun getName(): String?
    fun hasName(): Boolean
}