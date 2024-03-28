package com.ps_pn.firstblockpractice.presentation.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding4.widget.textChanges
import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.databinding.FragmentAuthorizationBinding
import com.ps_pn.firstblockpractice.presentation.utills.WithoutBottomBar
import com.ps_pn.firstblockpractice.presentation.utills.navigator
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction

class AuthorizationFragment : Fragment(), WithoutBottomBar {

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("AuthorizationFragment is null")
    private val disposableBag = CompositeDisposable()

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
        setListeners()
    }

    private fun setListeners() {
        setBackButton()
        setLoginButton()
    }

    private fun setLoginButton() {
        val emailObservable = binding.emailEdit.textChanges()
        val passObservable = binding.passEdit.textChanges()
        val disposable = Observable.combineLatest(
            emailObservable, passObservable, BiFunction { t1, t2 ->
                t1.length >= 6 && t2.length >= 6
            }).subscribe {
            binding.loginBtn.isEnabled = it
        }
        disposableBag.add(disposable)
        binding.loginBtn.setOnClickListener {
            val navOptions: NavOptions = NavOptions.Builder()
                .setPopUpTo(R.id.authorizationFragment, true)
                .build()
            val direction =
                AuthorizationFragmentDirections.actionAuthorizationFragmentToHelpFragment2()
            findNavController().navigate(direction, navOptions)
        }
    }

    private fun setBackButton() {
        binding.imageButtonBack.setOnClickListener {
            navigator().exit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposableBag.clear()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AuthorizationFragment()
    }
}