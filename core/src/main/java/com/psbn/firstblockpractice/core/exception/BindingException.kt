package com.psbn.firstblockpractice.core.exception

class BindingException(private val msg: String) : Exception() {
    override val message: String
        get() = msg
}
