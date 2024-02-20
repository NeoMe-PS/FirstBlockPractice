package com.ps_pn.firstblockpractice.presentation.fragments.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.databinding.FragmentSplashBinding

private const val SLEEP_TIME: Long = 2000

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentSplashBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Имитируем загрузку данных
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_userProfileFragment)
        }, SLEEP_TIME)

        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
