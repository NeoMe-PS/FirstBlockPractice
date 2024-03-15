package com.ps_pn.firstblockpractice.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.activity.setViewTreeOnBackPressedDispatcherOwner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.databinding.ActivityMainBinding
import com.ps_pn.firstblockpractice.presentation.fragments.help.HelpFragment
import com.ps_pn.firstblockpractice.presentation.fragments.history.HistoryFragment
import com.ps_pn.firstblockpractice.presentation.fragments.news.FilterFragment
import com.ps_pn.firstblockpractice.presentation.fragments.news.NewsDetailFragment
import com.ps_pn.firstblockpractice.presentation.fragments.news.NewsFragment
import com.ps_pn.firstblockpractice.presentation.fragments.search.SearchFragment
import com.ps_pn.firstblockpractice.presentation.fragments.splash.SplashFragment
import com.ps_pn.firstblockpractice.presentation.fragments.user.EditProfileFragment
import com.ps_pn.firstblockpractice.presentation.fragments.user.UserProfileFragment
import com.ps_pn.firstblockpractice.presentation.models.Event
import com.ps_pn.firstblockpractice.presentation.utills.HasCustomBottomBar
import com.ps_pn.firstblockpractice.presentation.utills.Navigator


private const val SLEEP_TIME: Long = 2000
private const val FRAGMENT_BOTTOM_MARGIN = -20
const val FILTER_PREFERENCES = "FILTER_PREFERENCES"

class MainActivity : AppCompatActivity(), Navigator {
    private var _binding: ActivityMainBinding? = null

    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStartState(savedInstanceState)
        showSplashScreen(savedInstanceState)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.setViewTreeOnBackPressedDispatcherOwner(this)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profileFragment -> openUserFragment()
                R.id.newsFragment -> openNewsFragment()
                R.id.searchFragment -> openSearchFragment()
                R.id.helpFragment -> openHelpFragment()
                R.id.historyFragment -> openHistoryFragment()
            }
            true
        }
        if (supportFragmentManager.fragments.last() is HasCustomBottomBar) {
            hideMainBottomNav()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (binding.bottomNavigationView.selectedItemId == R.id.helpFragment) {
            finish()
        }
        showMainBottomNav()
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        binding.bottomNavigationView.selectedItemId = R.id.helpFragment
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showSplashScreen(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val splashFragment = SplashFragment.newInstance()
            setSplash(splashFragment)

            Handler(Looper.getMainLooper()).postDelayed({
                removeSplash(splashFragment)
            }, SLEEP_TIME)
        }
    }

    private fun setSplash(splashFragment: SplashFragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.nav_host_fragment, splashFragment)
            .commit()
        binding.mainActivityCoordinator.visibility = View.GONE
    }

    private fun removeSplash(splashFragment: SplashFragment) {
        supportFragmentManager.beginTransaction()
            .remove(splashFragment)
            .commit()
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        binding.mainActivityCoordinator.visibility = View.VISIBLE
    }

    private fun setStartState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.helpFragment
        }
    }

    override fun openHelpFragment() {
        loadFragment(HelpFragment.newInstance())
    }

    override fun openHistoryFragment() {
        loadFragment(HistoryFragment.newInstance())
    }

    override fun openNewsFragment() {
        loadFragment(NewsFragment.newInstance())
    }

    override fun openNewsFilterFragment() {
        loadFragment(FilterFragment.newInstance())
    }

    override fun openSearchFragment() {
        loadFragment(SearchFragment.newInstance())
    }

    override fun openUserFragment() {
        loadFragment(UserProfileFragment.newInstance())
    }

    override fun openUserEditFragment() {
        loadFragment(EditProfileFragment.newInstance())
    }

    override fun openNewsDetailFragment(event: Event) {
        hideMainBottomNav()
        loadFragment(NewsDetailFragment.newInstance(event))
    }

    override fun hideMainBottomNav() {
        val param = binding.navHostFragment.layoutParams as MarginLayoutParams
        param.setMargins(0)
        binding.navHostFragment.layoutParams = param
        binding.mainActivityCoordinator.visibility = View.GONE
    }

    override fun showMainBottomNav() {
        if (binding.mainActivityCoordinator.visibility == View.GONE) {
            binding.mainActivityCoordinator.visibility = View.VISIBLE
            val param = binding.navHostFragment.layoutParams as MarginLayoutParams
            param.setMargins(0, 0, 0, FRAGMENT_BOTTOM_MARGIN)
            binding.navHostFragment.layoutParams = param
        }
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }
}
