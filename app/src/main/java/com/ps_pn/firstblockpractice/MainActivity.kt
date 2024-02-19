package com.ps_pn.firstblockpractice

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ps_pn.firstblockpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null

    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)
        setupBottomNavigation(navController)
        hideBottomMenuOnSplash(navController)
    }

    private fun setupBottomNavigation(navController: NavController) {
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun hideBottomMenuOnSplash(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val currentFragment = destination.id
            val splashFragment = R.id.splashFragment

            when (currentFragment) {
                splashFragment -> {
                    binding.mainActivityCoordinator.visibility = View.GONE }
                else -> {
                    binding.mainActivityCoordinator.visibility = View.VISIBLE
                }
            }
        }
    }
}