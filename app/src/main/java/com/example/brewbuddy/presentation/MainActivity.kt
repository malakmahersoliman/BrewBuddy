package com.example.brewbuddy.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.brewbuddy.R
import com.example.brewbuddy.core.navigation.setupWithNavControllerPreservingState
import com.example.brewbuddy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        com.example.brewbuddy.data.local.prefs.Prefs.userName = null //this clear the name

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Handle system bar insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ✅ Get NavController safely
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

        // ✅ set Toolbar as ActionBar
                setSupportActionBar(binding.toolbar)

        // ✅ Make Toolbar titles follow nav_graph labels
                val appBarConfiguration = AppBarConfiguration(
                    setOf(R.id.homeFragment, R.id.menuFragment, R.id.favoritesFragment, R.id.ordersFragment)
                )
                setupActionBarWithNavController(navController, appBarConfiguration)

        // ✅ Connect BottomNavigationView with NavController
                binding.bottomNavigation.setupWithNavControllerPreservingState(navController)

        // ✅ Hide Toolbar/BottomNav on onboarding screens
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    val showChrome = destination.id in setOf(
                        R.id.homeFragment, R.id.menuFragment, R.id.favoritesFragment, R.id.ordersFragment
                    )
                    binding.toolbar.visibility = if (showChrome) View.VISIBLE else View.GONE
                    binding.bottomNavigation.visibility = if (showChrome) View.VISIBLE else View.GONE
        }

    }
}
