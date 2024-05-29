package com.psbn.firstblockpractice.auth.presentation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.psbn.firstblockpractice.core.uiUtills.WithoutBottomBar
import com.psbn.firstblockpractice.auth.R as authR

private const val DESTINATION_URI = "app://helpFragment"

class AuthorizationFragment : Fragment(), WithoutBottomBar {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                AuthContent(
                    onBackIconClickListener = { exitFromApp() },
                    onLoginButtonClickListener = { navigateToNextScreen() }
                )
            }
        }
    }

    private fun exitFromApp() {
        requireActivity().finish()
    }

    private fun navigateToNextScreen() {
        val navOptions: NavOptions = NavOptions.Builder()
            .setPopUpTo(authR.id.authorizationFragment, true)
            .build()
        val uri = Uri.parse(DESTINATION_URI)
        findNavController().navigate(uri, navOptions)
    }
}
