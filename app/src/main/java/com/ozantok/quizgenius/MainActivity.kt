package com.ozantok.quizgenius

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavGraph

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.ozantok.quizgenius.databinding.ActivityMainBinding
import androidx.navigation.ui.setupWithNavController
import com.ozantok.quizgenius.presentation.util.makeStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.post {
            makeStatusBarTransparent(binding.root)
        }

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val onboardingCompleted = prefs.getBoolean("onboarding_completed", false)

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        val startDestination = if (onboardingCompleted) R.id.homeFragment else R.id.onboardingFragment
        navGraph.setStartDestination(startDestination)

        navController.graph = navGraph

        binding.bottomNavView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> binding.bottomNavView.visibility = View.VISIBLE
                else -> binding.bottomNavView.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
                ?.findNavController()
        return navController?.navigateUp() ?: super.onSupportNavigateUp()
    }
}