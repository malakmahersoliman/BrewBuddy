package com.example.brewbuddy.presentation

import android.os.Bundle
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
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // ✅ set Toolbar as ActionBar
        setSupportActionBar(binding.toolbar)

        // ✅ Make Toolbar titles follow nav_graph labels
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.menuFragment, R.id.favoritesFragment, R.id.ordersFragment)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        // ✅ Connect BottomNavigationView with NavController
        binding.bottomNavigation.setupWithNavControllerPreservingState(navController)
    }
}
