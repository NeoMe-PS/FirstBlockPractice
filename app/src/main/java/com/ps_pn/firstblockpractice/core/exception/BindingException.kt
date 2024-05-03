package com.ps_pn.firstblockpractice.core.exception

class BindingException(private val msg: String) : Exception() {
    override val message: String
        get() = msg
}
