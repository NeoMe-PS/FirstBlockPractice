package com.ps_pn.firstblockpractice.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.databinding.ActivityMainBinding
import com.ps_pn.firstblockpractice.presentation.utills.Navigator

const val FILTER_PREFERENCES = "FILTER_PREFERENCES"

class MainActivity : AppCompatActivity(), Navigator {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding is null")
    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        supportActionBar?.hide();
        setContentView(binding.root)
        setNavigation()
        hideBottomAtDestination()
    }

    private fun setNavigation() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.helpFragment,
                R.id.historyFragment,
                R.id.profileFragment,
                R.id.newsFragment,
                R.id.searchFragment,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView.labelVisibilityMode =
            NavigationBarView.LABEL_VISIBILITY_SELECTED
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun hideMainBottomNav() {
        binding.mainActivityCoordinator.visibility = View.GONE
    }

    override fun showMainBottomNav() {
        binding.mainActivityCoordinator.visibility = View.VISIBLE
    }

    private fun hideBottomAtDestination() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val currentFragmentId = destination.id
            if (isRequiringHide(currentFragmentId)) {
                hideMainBottomNav()
            } else {
                showMainBottomNav()
            }
        }
    }

    private fun isRequiringHide(currentFragmentId: Int): Boolean {
        val splashID = R.id.splashFragment
        val editProfileFragmentID = R.id.editProfileFragment
        val authID = R.id.authorizationFragment
        val newsDetailed = R.id.newsDetailFragment

        return currentFragmentId == splashID ||
                currentFragmentId == authID ||
                currentFragmentId == editProfileFragmentID ||
                currentFragmentId == newsDetailed
    }

    override fun showStartState() {
        binding.bottomNavigationView.selectedItemId = R.id.helpFragment
        showMainBottomNav()
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }

    override fun exit() {
        finish()
    }
}
