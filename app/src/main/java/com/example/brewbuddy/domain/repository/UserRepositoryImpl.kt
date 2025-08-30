package com.example.brewbuddy.data.repository

import com.example.brewbuddy.data.local.prefs.Prefs
import com.example.brewbuddy.domain.repo.UserRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor() : UserRepo {

    override fun saveName(name: String) {
        Prefs.userName = name.trim()
    }

    override fun getName(): String? = Prefs.userName

    override fun hasName(): Boolean = Prefs.isOnboarded
}
