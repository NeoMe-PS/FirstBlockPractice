package com.psbn.firstblockpractice.help.data.mapper

import junit.framework.TestCase.assertEquals
import org.junit.Test

class CategoryMapperTest {

    private val mapper = CategoryMapper()

    @Test
    fun mapJsonToDb() {
        val outputData = mapper.mapJSONCategoryToDb(inputJSON)
        assertEquals(expectedDb, outputData)
    }

    @Test
    fun mapDtoToDb() {
        val outputData = mapper.mapDtoToDbModel(inputDto)
        assertEquals(expectedDb, outputData)
    }

    @Test
    fun mapDbToDomain() {
        val outputData = mapper.mapDbToPresentation(expectedDb)
        assertEquals(expectedDomain, outputData)
    }
}