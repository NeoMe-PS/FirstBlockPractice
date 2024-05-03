package com.ps_pn.firstblockpractice.presentation.utills

class BindingException(private val msg: String) : Exception() {
    override val message: String
        get() = msg
}
