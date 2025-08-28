// NavExtensions.kt
package com.example.brewbuddy.core.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Wires BottomNavigationView to a single NavHost with state preservation across tabs.
 * Prevents crashes by avoiding navigation to the current destination.
 */
fun BottomNavigationView.setupWithNavControllerPreservingState(navController: NavController) {
    setOnItemSelectedListener { item ->
        val currentDestination = navController.currentDestination?.id
        if (currentDestination != item.itemId) {
            val options = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setRestoreState(true)
                .setPopUpTo(navController.graph.startDestinationId, inclusive = false, saveState = true)
                .build()

            return@setOnItemSelectedListener try {
                navController.navigate(item.itemId, null, options)
                true
            } catch (_: IllegalArgumentException) {
                false
            }
        } else {
            false
        }
    }

    setOnItemReselectedListener { item ->
        // Pop back stack to the selected tab's root
        navController.popBackStack(item.itemId, false)
    }

    // Keep BottomNavigationView in sync with NavController
    navController.addOnDestinationChangedListener { _: NavController, destination: NavDestination, _: Bundle? ->
        val match = menu.findItem(destination.id)
        if (match != null && match.itemId != selectedItemId) {
            selectedItemId = match.itemId
        }
    }
}
