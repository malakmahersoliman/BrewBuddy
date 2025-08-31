package com.example.brewbuddy.data.local.prefs

import android.content.Context
import android.content.SharedPreferences

object Prefs {
    private const val FILE = "brewbuddy_prefs"
    private const val KEY_USER_NAME = "user_name"

    private lateinit var sp: SharedPreferences

    fun init(context: Context) {
        if (!::sp.isInitialized)
            sp = context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
    }

    var userName: String?
        get() = if (::sp.isInitialized) sp.getString(KEY_USER_NAME, null) else null
        set(value) { sp.edit().putString(KEY_USER_NAME, value).apply() }

    val isOnboarded: Boolean
        get() = !userName.isNullOrBlank()
}
