package com.psbn.user.data.mapper

import com.psbn.firstblockpractice.core.data.network.dto.FriendDto
import junit.framework.TestCase.assertEquals
import org.junit.Test

class FriendMapperTest {
    private val mapper = FriendMapper()

    @Test
    fun mapDtoToPresentation() {
        val inputData = listOf(
            FriendDto(id = 1, name = "Kolya", img = "image1")
        )
        val outputData = inputData.map { mapper.mapDtoFriendToPresentation(it) }

        assertEquals(outputData.size, inputData.size)

        outputData.forEach {
            assertEquals(it.id, 1)
            assertEquals(it.name, "Kolya")
            assertEquals(it.img, "image1")
        }
    }
}