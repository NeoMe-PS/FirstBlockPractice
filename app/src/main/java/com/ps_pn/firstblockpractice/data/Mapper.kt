package com.ps_pn.firstblockpractice.data

import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.data.db.entity.CategoryDbModel
import com.ps_pn.firstblockpractice.data.db.entity.EventDbModel
import com.ps_pn.firstblockpractice.data.network.dto.CategoryDto
import com.ps_pn.firstblockpractice.data.network.dto.EventDto
import com.ps_pn.firstblockpractice.data.network.dto.FriendDto
import com.ps_pn.firstblockpractice.presentation.adapters.help.CategoryAdapterEntity
import com.ps_pn.firstblockpractice.presentation.models.Category
import com.ps_pn.firstblockpractice.presentation.models.Event
import com.ps_pn.firstblockpractice.presentation.models.Filter
import com.ps_pn.firstblockpractice.presentation.models.Friend
import com.ps_pn.firstblockpractice.presentation.utills.TimeFormatter
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.periodUntil

object Mapper {

    fun mapJSONCategoryToDb(category: Category) = CategoryDbModel(
        id = category.id,
        label = category.label,
        img = getCategoryResId(category.id).toString()
    )

    fun mapJSONEventToDb(eventDataModel: EventDataModel): EventDbModel {

        return EventDbModel(
            id = eventDataModel.id,
            categories = eventDataModel.categories,
            label = eventDataModel.label,
            shortDesc = eventDataModel.shortDesc,
            fullDesc = eventDataModel.fullDesc,
            date = eventDataModel.date.toString(),
            dateStart = eventDataModel.dateStart,
            dateEnd = eventDataModel.dateEnd,
            thumbnail = eventDataModel.thumbnail,
            newsImages = eventDataModel.newsImages,
            address = eventDataModel.address,
            phone = eventDataModel.phone,
            company = eventDataModel.company,
        )
    }

    fun mapDtoFriendToPresentation(friend: FriendDto) = Friend(
        id = friend.id,
        name = friend.name,
        img = friend.img
    )


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

    fun mapDtoToDbModel(categoryDto: CategoryDto) = CategoryDbModel(
        id = categoryDto.id,
        label = categoryDto.label,
        img = getCategoryResId(categoryDto.id).toString()
    )

    fun mapDbToPresentation(eventDbModel: EventDbModel): Event {
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
            categories = eventDbModel.categories,
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
            diffInDays = period?.days.toString()
        )
    }

    fun mapDbToPresentation(categoryDbModel: CategoryDbModel) = CategoryAdapterEntity(
        id = categoryDbModel.id,
        name = categoryDbModel.label,
        image = getCategoryResId(categoryDbModel.id)
    )

    private fun getCategoryResId(idFrom: Int) = when (idFrom) {
        Filter.Kids.id -> R.drawable.icon_kids
        Filter.Adults.id -> R.drawable.icon_adult
        Filter.Elderly.id -> R.drawable.icon_elderly
        Filter.Animals.id -> R.drawable.icon_animals
        Filter.Events.id -> R.drawable.icon_event
        else -> R.drawable.bg_white_rounded
    }
}
