package com.ps_pn.firstblockpractice.data

import androidx.lifecycle.MutableLiveData
import com.github.javafaker.Faker
import com.ps_pn.firstblockpractice.presentation.adapters.friend.Friend
import com.ps_pn.firstblockpractice.presentation.adapters.help.CategoryAdapterEntity
import com.ps_pn.firstblockpractice.presentation.adapters.search.SearchResultEntity
import com.ps_pn.firstblockpractice.presentation.models.Event
import com.ps_pn.firstblockpractice.presentation.models.Filter

class StubData {

    companion object {
        private val faker = Faker()
        var categoriesData = listOf<CategoryAdapterEntity>()
        var categoriesIsLoaded = MutableLiveData(false)
        var newsIsLoaded = MutableLiveData(false)
        var newsData = listOf<Event>()

        fun fillFriendsStubData(): List<Friend> {
            val friends = mutableListOf<Friend>()
            friends.add(Friend(id = 1, name = "Дмитрий Валерьевич", imageUrl = ""))
            friends.add(Friend(id = 2, name = "Евгений Александров", imageUrl = ""))
            friends.add(Friend(id = 3, name = "Виктор Кузнецов", imageUrl = ""))
            return friends
        }

        fun fillCategoriesStubData(): List<CategoryAdapterEntity> {
            if (categoriesIsLoaded.value == true) {
                return categoriesData
            }
            categoriesData = JSONParser.getCategoriesFromJson()
                .map { Mapper.mapJSONCategoryToPresentation(it) }
            categoriesIsLoaded.postValue(true)
            return categoriesData
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
            if (newsIsLoaded.value == true) {
                return newsData
            }
            newsData = JSONParser.getNewsFromJson().toMutableList()
                .map { Mapper.mapJSONEventToPresentation(it) }
            newsIsLoaded.postValue(true)
            return newsData
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
