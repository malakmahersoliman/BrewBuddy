package com.example.brewbuddy.data.repository

import com.example.brewbuddy.data.local.prefs.Prefs
import com.example.brewbuddy.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor() : UserRepository {

    override fun saveName(name: String) {
        Prefs.userName = name.trim()
    }

    override fun getName(): String? = Prefs.userName

    override fun hasName(): Boolean = Prefs.isOnboarded
}