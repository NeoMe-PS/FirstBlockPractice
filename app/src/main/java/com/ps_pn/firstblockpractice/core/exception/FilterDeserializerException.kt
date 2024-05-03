package com.ps_pn.firstblockpractice.core.exception

class FilterDeserializerException(private val id: String) : Exception() {
    override val message: String
        get() = "Unknown type of filter settings : $id "
}