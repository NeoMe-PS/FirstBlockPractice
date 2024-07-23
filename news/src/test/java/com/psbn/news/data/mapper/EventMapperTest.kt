package com.psbn.news.data.mapper

import junit.framework.TestCase.assertEquals
import org.junit.Test

class EventMapperTest {
    private val mapper = EventMapper()

    @Test
    fun mapDtoToPresentation() {
        val inputData = listOf(inputDto)

        val outputData = inputData.map { mapper.mapDtoToDbModel(it) }

        assertEquals(outputData.size, inputData.size)
        outputData.forEach {
            assertEquals(it.id, expectedDB.id)
            assertEquals(it.categories, expectedDB.categories)
            assertEquals(it.label, expectedDB.label)
            assertEquals(it.shortDesc, expectedDB.shortDesc)
            assertEquals(it.fullDesc, expectedDB.fullDesc)
            assertEquals(it.date, expectedDB.date)
            assertEquals(it.dateStart, expectedDB.dateStart)
            assertEquals(it.dateEnd, expectedDB.dateEnd)
            assertEquals(it.thumbnail, expectedDB.thumbnail)
            assertEquals(it.newsImages, expectedDB.newsImages)
            assertEquals(it.address, expectedDB.address)
            assertEquals(it.phone, expectedDB.phone)
            assertEquals(it.company, expectedDB.company)
        }
    }

    @Test
    fun mapDBToDomain() {
        val outputData = mapper.mapDbToDomain(expectedDB)
        assertEquals(expectedPres, outputData)
    }

    @Test
    fun mapJSONToDb() {
        val inputData = inputJSON

        val outputData = mapper.mapJSONEventToDb(inputData)
        assertEquals(expectedDB, outputData)
    }
}


