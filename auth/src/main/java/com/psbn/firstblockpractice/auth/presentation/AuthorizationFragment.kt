package com.psbn.firstblockpractice.auth.presentation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.jakewharton.rxbinding4.widget.textChanges
import com.psbn.firstblockpractice.auth.R
import com.psbn.firstblockpractice.auth.databinding.FragmentAuthorizationBinding
import com.psbn.firstblockpractice.core.exception.BindingException
import com.psbn.firstblockpractice.core.uiUtills.WithoutBottomBar
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

private const val DESTINATION_URI = "app://helpFragment"

class AuthorizationFragment : Fragment(), WithoutBottomBar {

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding
        get() = _binding ?: throw BindingException("AuthorizationFragment is null")
    private val disposableBag = CompositeDisposable()

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
        checkAuthorization()
    }

    private fun checkAuthorization() {
        val emailObservable = binding.emailEdit.textChanges()
        val passObservable = binding.passEdit.textChanges()
        val disposable = Observable.combineLatest(emailObservable, passObservable)
        { emailText, passText ->
            emailText.length >= MIN_LENGTH_VALUE && passText.length >= MIN_LENGTH_VALUE
        }
            .subscribe {
                binding.loginBtn.isEnabled = it
            }
        disposableBag.add(disposable)
    }

    private fun setLoginButton() {
        binding.loginBtn.setOnClickListener {
            val navOptions: NavOptions = NavOptions.Builder()
                .setPopUpTo(R.id.authorizationFragment, true)
                .build()
            val uri = Uri.parse(DESTINATION_URI)
            findNavController().navigate(uri, navOptions)
        }
    }

    private fun setBackButton() {
        binding.imageButtonBack.setOnClickListener {
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposableBag.clear()
    }

    companion object {
        private const val MIN_LENGTH_VALUE = 6
    }
}
