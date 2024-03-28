package com.ps_pn.firstblockpractice.presentation.utills

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator = requireActivity() as Navigator

interface Navigator {
    fun hideMainBottomNav()
    fun showMainBottomNav()
    fun showStartState()

    fun back()
    fun exit()
}
