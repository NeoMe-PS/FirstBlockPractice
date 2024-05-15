package com.psbn.firstblockpractice.presentation.navigateutill

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator = requireActivity() as Navigator

interface Navigator {
    fun hideMainBottomNav()
    fun showMainBottomNav()
    fun setNewsBadges(count: Int)
    fun exit()
}
