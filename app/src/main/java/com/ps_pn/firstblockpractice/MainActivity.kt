package com.ps_pn.firstblockpractice

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.setViewTreeOnBackPressedDispatcherOwner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ps_pn.firstblockpractice.databinding.ActivityMainBinding
import com.ps_pn.firstblockpractice.presentation.fragments.help.HelpFragment
import com.ps_pn.firstblockpractice.presentation.fragments.history.HistoryFragment
import com.ps_pn.firstblockpractice.presentation.fragments.news.NewsFragment
import com.ps_pn.firstblockpractice.presentation.fragments.search.SearchFragment
import com.ps_pn.firstblockpractice.presentation.fragments.splash.SplashFragment
import com.ps_pn.firstblockpractice.presentation.fragments.user.UserProfileFragment

private const val SLEEP_TIME: Long = 2000

class MainActivity : AppCompatActivity() {
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
                R.id.profileFragment -> loadFragment(UserProfileFragment.newInstance())
                R.id.newsFragment -> loadFragment(NewsFragment.newInstance())
                R.id.searchFragment -> loadFragment(SearchFragment.newInstance())
                R.id.helpFragment -> loadFragment(HelpFragment.newInstance())
                R.id.historyFragment -> loadFragment(HistoryFragment.newInstance())
            }
            true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (binding.bottomNavigationView.selectedItemId == R.id.helpFragment) {
            finish()
        }
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        binding.bottomNavigationView.selectedItemId = R.id.helpFragment
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out)
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
}
