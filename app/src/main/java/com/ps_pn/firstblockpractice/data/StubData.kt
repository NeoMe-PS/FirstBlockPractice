package com.ps_pn.firstblockpractice.data

import com.github.javafaker.Faker
import com.ps_pn.firstblockpractice.presentation.adapters.friend.Friend
import com.ps_pn.firstblockpractice.presentation.adapters.help.CategoryAdapterEntity
import com.ps_pn.firstblockpractice.presentation.adapters.search.SearchResultEntity
import com.ps_pn.firstblockpractice.presentation.models.Event
import com.ps_pn.firstblockpractice.presentation.models.Filter

class StubData {

    companion object {
        private val faker = Faker()
        fun fillFriendsStubData(): List<Friend> {
            val friends = mutableListOf<Friend>()
            friends.add(Friend(id = 1, name = "Дмитрий Валерьевич", imageUrl = ""))
            friends.add(Friend(id = 2, name = "Евгений Александров", imageUrl = ""))
            friends.add(Friend(id = 3, name = "Виктор Кузнецов", imageUrl = ""))
            return friends
        }

        fun fillCategoriesStubData(): List<CategoryAdapterEntity> {
            return JSONParser.getCategoriesFromJson()
                .map { Mapper.mapJSONCategoryToPresentation(it) }
        }

        fun fillSearchResultsStubData(): List<SearchResultEntity> {
            val results = mutableListOf<SearchResultEntity>()
            val randomQuantity = (0..15).random()
            for (i in 0..randomQuantity) {
                results.add(SearchResultEntity(title = faker.company().name()))
            }
            return results
        }

        fun fillNewsStubData(): List<Event> {
            return JSONParser.getNewsFromJson().toMutableList()
                .map { Mapper.mapJSONEventToPresentation(it) }
        }

        fun filterNewsStubData(
            currentList: List<Event>,
            filterCategories: List<Filter>
        ): List<Event> {
            val activeFilters = filterCategories.filter { it.isActive }.map { it.id }
            if (activeFilters.isEmpty()) {
                return emptyList()
            }
            val result = mutableListOf<Event>()
            for (news in currentList) {
                val filtersInNewsItem = news.categories.map { it.id }
                for (filterInItem in filtersInNewsItem) {
                    if (activeFilters.any { activeFilter -> activeFilter == filterInItem }) {
                        result.add(news)
                        break
                    }
                }
            }
            return result
        }
    }
}
