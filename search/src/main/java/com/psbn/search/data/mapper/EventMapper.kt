package com.psbn.search.data.mapper

import com.psbn.firstblockpractice.core.data.db.entity.EventDbModel
import com.psbn.firstblockpractice.core.data.jsonstorage.models.EventJSON
import com.psbn.firstblockpractice.core.data.network.dto.EventDto
import com.psbn.firstblockpractice.core.date.TimeFormatter
import com.psbn.search.domain.entity.Category
import com.psbn.search.domain.entity.Event
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.periodUntil
import javax.inject.Inject

class EventMapper @Inject constructor() {
    fun mapJSONEventToDb(eventJSON: EventJSON): EventDbModel {

        return EventDbModel(
            id = eventJSON.id,
            categories = eventJSON.categories,
            label = eventJSON.label,
            shortDesc = eventJSON.shortDesc,
            fullDesc = eventJSON.fullDesc,
            date = eventJSON.date.toString(),
            dateStart = eventJSON.dateStart,
            dateEnd = eventJSON.dateEnd,
            thumbnail = eventJSON.thumbnail,
            newsImages = eventJSON.newsImages,
            address = eventJSON.address,
            phone = eventJSON.phone,
            company = eventJSON.company,
        )
    }

    fun mapDtoToDbModel(eventDto: EventDto): EventDbModel {
        return EventDbModel(
            id = eventDto.id,
            categories = eventDto.categories,
            label = eventDto.label,
            shortDesc = eventDto.shortDesc,
            fullDesc = eventDto.fullDesc,
            date = eventDto.date,
            dateStart = eventDto.dateStart,
            dateEnd = eventDto.dateEnd,
            thumbnail = eventDto.thumbnail,
            newsImages = eventDto.newsImages,
            address = eventDto.address,
            phone = eventDto.phone,
            company = eventDto.company,
        )
    }

    fun mapDbToDomain(eventDbModel: EventDbModel): Event {
        val formattedDate = TimeFormatter.formatFullDate(eventDbModel.date.toLong())
        val formattedStartDate = TimeFormatter.formatPeriodDate(eventDbModel.dateStart)
        val formattedEndDate = TimeFormatter.formatPeriodDate(eventDbModel.dateEnd)

        val eventDateInstant = Instant.fromEpochMilliseconds(eventDbModel.date.toLong())
        var period: DateTimePeriod? = null

        var resultDate: String = formattedDate
        if (eventDbModel.dateEnd != 0L) {
            period = eventDateInstant.periodUntil(Clock.System.now(), TimeZone.UTC)
            resultDate =
                "Осталось ${period.days} дней ($formattedStartDate - $formattedEndDate)"
        }
        return Event(
            id = eventDbModel.id,
            categories = eventDbModel.categories.map { Category(it.id, it.label, it.img) },
            label = eventDbModel.label,
            shortDesc = eventDbModel.shortDesc,
            fullDesc = eventDbModel.fullDesc,
            date = resultDate,
            dateStart = formattedStartDate,
            dateEnd = formattedEndDate,
            thumbnail = eventDbModel.thumbnail,
            newsImages = eventDbModel.newsImages,
            address = eventDbModel.address,
            phone = eventDbModel.phone,
            company = eventDbModel.company,
            diffInDays = period?.days.toString(),
            isRead = eventDbModel.isRead
        )
    }
}