package com.example.brewbuddy.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.brewbuddy.R
import com.example.brewbuddy.core.navigation.setupWithNavControllerPreservingState
import com.example.brewbuddy.databinding.ActivityMainBinding
import com.example.brewbuddy.domain.usecase.GetUserNameUseCase
import com.example.brewbuddy.utils.ToolbarManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // (Optional) Clear username for testing
        com.example.brewbuddy.data.local.prefs.Prefs.userName = null

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle system bar insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get NavController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (savedInstanceState == null) {
            val graph = navController.navInflater.inflate(R.navigation.nav_graph)
            graph.setStartDestination(
                if (com.example.brewbuddy.data.local.prefs.Prefs.isOnboarded)
                    R.id.homeFragment else R.id.onboardingFragment
            )
            navController.graph = graph
        }

        // Connect BottomNavigationView with NavController
        binding.bottomNavigation.setupWithNavControllerPreservingState(navController)

        // ✅ Listen for navigation changes
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                // Hide both on onboarding + enterName
                R.id.onboardingFragment,
                R.id.enterNameFragment -> {
                    binding.toolbar.root.visibility = View.GONE
                    binding.bottomNavigation.visibility = View.GONE
                }

                // Show & customize for others
                R.id.homeFragment -> {
                    binding.toolbar.root.visibility = View.VISIBLE
                    binding.bottomNavigation.visibility = View.VISIBLE
                    ToolbarManager.setCustomGreeting(
                        this,
                        com.example.brewbuddy.data.local.prefs.Prefs.userName
                    )
                }

                R.id.DrinkMenuFragment -> {
                    binding.toolbar.root.visibility = View.VISIBLE
                    binding.bottomNavigation.visibility = View.VISIBLE
                    ToolbarManager.updateToolbarTitle(this, "What would you like to drink today?")
                }

                R.id.ordersFragment -> {
                    binding.toolbar.root.visibility = View.VISIBLE
                    binding.bottomNavigation.visibility = View.VISIBLE
                    ToolbarManager.updateToolbarTitle(this, "Your orders")
                }

                R.id.favoritesFragment -> {
                    binding.toolbar.root.visibility = View.VISIBLE
                    binding.bottomNavigation.visibility = View.VISIBLE
                    ToolbarManager.updateToolbarTitle(this, "Your favorite drinks to lighten up your day")
                }
            }
        }
    }
}
