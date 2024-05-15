package com.psbn.firstblockpractice.core.exception

class FilterPrefException(private val id: Int) : Exception() {
    override val message: String
        get() = "Filter id not found in save settings id: $id"
}
