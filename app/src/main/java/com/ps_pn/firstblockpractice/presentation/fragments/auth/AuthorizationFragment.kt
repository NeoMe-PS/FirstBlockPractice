package com.ps_pn.firstblockpractice.presentation.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.databinding.FragmentAuthorizationBinding
import com.ps_pn.firstblockpractice.presentation.utills.navigator

class AuthorizationFragment : Fragment() {

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("AuthorizationFragment is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackButton()
        binding.loginBtn.setOnClickListener {
            navigator().showStartState()
        }
    }

    private fun setBackButton() {
        binding.imageButtonBack.setOnClickListener {
            navigator().exit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthorizationFragment()
    }
}