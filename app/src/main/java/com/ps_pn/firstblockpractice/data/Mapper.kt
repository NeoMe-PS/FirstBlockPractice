package com.ps_pn.firstblockpractice.data

import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.presentation.adapters.help.CategoryAdapterEntity
import com.ps_pn.firstblockpractice.presentation.models.Adults
import com.ps_pn.firstblockpractice.presentation.models.Animals
import com.ps_pn.firstblockpractice.presentation.models.Category
import com.ps_pn.firstblockpractice.presentation.models.Elderly
import com.ps_pn.firstblockpractice.presentation.models.Event
import com.ps_pn.firstblockpractice.presentation.models.Events
import com.ps_pn.firstblockpractice.presentation.models.Kids
import com.ps_pn.firstblockpractice.presentation.utills.TimeFormatter
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.periodUntil

class Mapper {

    companion object {
        fun mapJSONCategoryToPresentation(category: Category): CategoryAdapterEntity {
            val imgResId = when (category.id) {
                Kids.id -> R.drawable.icon_kids
                Adults.id -> R.drawable.icon_adult
                Elderly.id -> R.drawable.icon_elderly
                Animals.id -> R.drawable.icon_animals
                Events.id -> R.drawable.icon_event
                else -> R.drawable.bg_white_rounded
            }
            return CategoryAdapterEntity(id = category.id, name = category.label, image = imgResId)
        }

        fun mapJSONEventToPresentation(eventDataModel: EventDataModel): Event {

            val formattedDate = TimeFormatter.formatFullDate(eventDataModel.date)
            val formattedStartDate = TimeFormatter.formatPeriodDate(eventDataModel.dateStart)
            val formattedEndDate = TimeFormatter.formatPeriodDate(eventDataModel.dateEnd)

            val eventDateInstant = Instant.fromEpochMilliseconds(eventDataModel.date)
            var period: DateTimePeriod? = null

            var resultDate: String = formattedDate
            if (eventDataModel.dateEnd != 0L) {
                period = eventDateInstant.periodUntil(Clock.System.now(), TimeZone.UTC)
                resultDate =
                    "Осталось ${period.days} дней ($formattedStartDate - $formattedEndDate)"
            }
            return Event(
                id = eventDataModel.id,
                categories = eventDataModel.categories,
                label = eventDataModel.label,
                shortDesc = eventDataModel.shortDesc,
                fullDesc = eventDataModel.fullDesc,
                date = resultDate,
                dateStart = formattedStartDate,
                dateEnd = formattedEndDate,
                thumbnail = eventDataModel.thumbnail,
                newsImages = eventDataModel.newsImages,
                address = eventDataModel.address,
                phone = eventDataModel.phone,
                company = eventDataModel.company,
                diffInDays = period?.days.toString()
            )
        }
    }
}