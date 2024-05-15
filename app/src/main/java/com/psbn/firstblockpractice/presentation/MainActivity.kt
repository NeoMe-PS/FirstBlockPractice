package com.psbn.firstblockpractice.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.navigation.NavigationBarView
import com.psbn.firstblockpractice.R
import com.psbn.firstblockpractice.core.App
import com.psbn.firstblockpractice.core.exception.BindingException
import com.psbn.firstblockpractice.databinding.ActivityMainBinding
import com.psbn.firstblockpractice.di.AppComponent
import com.psbn.firstblockpractice.presentation.navigateutill.Navigator

const val FILTER_PREFERENCES = "FILTER_PREFERENCES"

class MainActivity : AppCompatActivity(), Navigator {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw BindingException(
            "ActivityMainBinding is null"
        )
    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }
    private var newsBadges: BadgeDrawable? = null

    private val component: AppComponent by lazy {
        (application as App).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        setNavigation()
        hideBottomAtDestination()
    }

    private fun setNavigation() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.help_navgraph,
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
        newsBadges = binding.bottomNavigationView.getOrCreateBadge(R.id.newsFragment)

    }

    override fun setNewsBadges(count: Int) {
        newsBadges?.let {
            it.isVisible = true
            it.number = count
        }
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
        val splashID = com.psbn.firstblockpractice.splash.R.id.splashFragment
        val editProfileFragmentID = R.id.editProfileFragment
        val authID = com.psbn.firstblockpractice.auth.R.id.authorizationFragment
        val newsDetailed = R.id.newsDetailFragment

        return currentFragmentId == splashID ||
                currentFragmentId == authID ||
                currentFragmentId == editProfileFragmentID ||
                currentFragmentId == newsDetailed
    }

    override fun exit() {
        finish()
    }
}
