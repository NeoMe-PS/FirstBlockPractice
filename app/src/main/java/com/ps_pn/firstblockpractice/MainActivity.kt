package com.ps_pn.firstblockpractice

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
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
        supportActionBar?.hide()
        setupBottomNavigation()
        hideBottomMenuOnSplash()
    }

    private fun setupBottomNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.profileFragment,
                R.id.historyFragment,
                R.id.helpFragment,
                R.id.searchFragment,
                R.id.newsFragment,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun hideBottomMenuOnSplash() {
        val navController: NavController =
            Navigation.findNavController(this, R.id.nav_host_fragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val currentFragment = destination.id
            val splashFragment = R.id.splashFragment

            if (currentFragment == splashFragment) {
                supportActionBar?.hide()
                binding.mainActivityCoordinator.visibility = View.GONE
            } else {
                supportActionBar?.show()
                binding.mainActivityCoordinator.visibility = View.VISIBLE
            }
        }
    }
}