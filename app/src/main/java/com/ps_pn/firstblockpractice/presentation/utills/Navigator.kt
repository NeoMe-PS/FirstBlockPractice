package com.ps_pn.firstblockpractice.presentation.utills

import androidx.fragment.app.Fragment
import com.ps_pn.firstblockpractice.presentation.models.Event

fun Fragment.navigator(): Navigator = requireActivity() as Navigator

interface Navigator {
    fun openHelpFragment()

    fun openHistoryFragment()

    fun openNewsFragment()

    fun openNewsFilterFragment()

    fun openSearchFragment()

    fun openUserFragment()

    fun openUserEditFragment()
    fun openNewsDetailFragment(event: Event)
    fun hideMainBottomNav()
    fun showMainBottomNav()

    fun back()
}
