package com.ps_pn.firstblockpractice.presentation.fragments.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.ps_pn.firstblockpractice.R


private const val SLEEP_TIME: Long = 2000

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            val navOptions: NavOptions = NavOptions.Builder()
                .setPopUpTo(R.id.splashFragment, true)
                .build()
            val direction =
                SplashFragmentDirections.actionSplashFragmentToAuthorizationFragment()
            findNavController().navigate(direction, navOptions)
        }, SLEEP_TIME)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}
