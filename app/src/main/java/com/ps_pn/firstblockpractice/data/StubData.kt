package com.ps_pn.firstblockpractice.data

import androidx.lifecycle.MutableLiveData
import com.ps_pn.firstblockpractice.presentation.adapters.friend.Friend
import com.ps_pn.firstblockpractice.presentation.adapters.help.CategoryAdapterEntity
import com.ps_pn.firstblockpractice.presentation.adapters.search.SearchResultEntity
import com.ps_pn.firstblockpractice.presentation.fragments.search.SEARCH_BY_EVENT_TAG
import com.ps_pn.firstblockpractice.presentation.fragments.search.SEARCH_BY_ORG_TAG
import com.ps_pn.firstblockpractice.presentation.models.Event
import com.ps_pn.firstblockpractice.presentation.models.Filter
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class StubData {

    companion object {
        var categoriesData = listOf<CategoryAdapterEntity>()
        var categoriesIsLoaded = MutableLiveData(false)
        var newsIsLoaded = MutableLiveData(false)
        var searchedDataByEvent = MutableLiveData<List<SearchResultEntity>?>(null)
        var searchedDataByOrg = MutableLiveData<List<SearchResultEntity>?>(null)
        var newsData = listOf<Event>()
        var subject: Subject<Int> = BehaviorSubject.create()


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

        fun fillSearchResultsStubData(query: String, queryTag: Int) {
            when (queryTag) {
                SEARCH_BY_EVENT_TAG -> searchByEvent(query)
                SEARCH_BY_ORG_TAG -> searchByOrg(query)
            }
        }

        fun clearSearchedData(tag: Int) {
            when (tag) {
                SEARCH_BY_EVENT_TAG -> searchedDataByEvent.value = null
                SEARCH_BY_ORG_TAG -> searchedDataByOrg.value = null
            }
        }

        private fun searchByEvent(query: String) {
            searchedDataByEvent.value = newsData
                .filter { event -> event.label.contains(query, true) }
                .map { event -> SearchResultEntity(event.label) }
        }

        private fun searchByOrg(query: String) {
            searchedDataByOrg.value = newsData
                .filter { event -> event.company.contains(query, true) }
                .map { event -> SearchResultEntity(event.label) }
        }

        fun fillNewsStubData(): List<Event> {
            if (newsIsLoaded.value == true) {
                return newsData
            }
            newsData = JSONParser.getNewsFromJson().toMutableList()
                .map { Mapper.mapJSONEventToPresentation(it) }
            newsIsLoaded.postValue(true)
            subject.onNext(newsData.size)
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
