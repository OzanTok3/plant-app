package com.ozantok.plantapp

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ozantok.plantapp.databinding.ActivityMainBinding
import com.ozantok.plantapp.presentation.util.makeStatusBarTransparent
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
        val startDestination =
            if (onboardingCompleted) R.id.homeFragment else R.id.onboardingFragment
        navGraph.setStartDestination(startDestination)

        navController.graph = navGraph
        binding.bottomNavView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    binding.bottomNavView.visibility = View.VISIBLE
                    binding.fabQr.visibility = View.VISIBLE
                }

                else -> {
                    binding.bottomNavView.visibility = View.GONE
                    binding.fabQr.visibility = View.GONE
                }
            }
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->
            handleNavigationItemClick(item)
        }

        binding.fabQr.setOnClickListener {
            openQRScanner()
        }
    }

    private fun handleNavigationItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeFragment -> {
                showToast("Home Clicked!")
                return true
            }

            R.id.diagnoseFragment -> {
                showToast("Dianogse Clicked!")
                return true
            }

            R.id.myGardenFragment -> {
                showToast("My Garden Clicked!")
                return true
            }

            R.id.profileFragment -> {
                showToast("Profile Clicked")
                return true
            }
        }
        return false
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun openQRScanner() {
        Toast.makeText(this, "QR Scanner açılıyor", Toast.LENGTH_SHORT).show()
        try {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Kamera açılamadı: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
                ?.findNavController()
        return navController?.navigateUp() ?: super.onSupportNavigateUp()
    }
}