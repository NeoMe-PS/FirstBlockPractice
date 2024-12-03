package com.psbn.firstblockpractice.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.navigation.NavigationBarView
import com.psbn.firstblockpractice.MainNavGraphDirections
import com.psbn.firstblockpractice.R
import com.psbn.firstblockpractice.core.exception.BindingException
import com.psbn.firstblockpractice.core.uiUtills.Navigator
import com.psbn.firstblockpractice.databinding.ActivityMainBinding


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigation()
        hideBottomAtDestination()
        if (intent != null && intent.extras != null) {
            val id = intent?.extras?.getInt("id", 1)
            navController.navigate(MainNavGraphDirections.actionGlobalNewsNavgraph())
        }
    }

    private fun setNavigation() {
        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView.labelVisibilityMode =
            NavigationBarView.LABEL_VISIBILITY_SELECTED
        newsBadges = binding.bottomNavigationView.getOrCreateBadge(com.psbn.news.R.id.news_navgraph)

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
        val editProfileFragmentID = com.psbn.user.R.id.editProfileFragment
        val authID = com.psbn.firstblockpractice.auth.R.id.authorizationFragment
        val newsDetailed = com.psbn.news.R.id.newsDetailFragment

        return currentFragmentId == splashID ||
                currentFragmentId == authID ||
                currentFragmentId == editProfileFragmentID ||
                currentFragmentId == newsDetailed
    }

    override fun exit() {
        finish()
    }
}
