package com.ps_pn.firstblockpractice.data

import com.github.javafaker.Faker
import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.presentation.adapter.friend.Friend
import com.ps_pn.firstblockpractice.presentation.adapter.help.Category
import com.ps_pn.firstblockpractice.presentation.adapter.search.SearchResult

class StubData {
    companion object {
        val faker = Faker()
        fun fillFriendsStubData(): List<Friend> {
            val friends = mutableListOf<Friend>()
            friends.add(Friend(id = 1, name = "Дмитрий Валерьевич", imageUrl = ""))
            friends.add(Friend(id = 2, name = "Евгений Александров", imageUrl = ""))
            friends.add(Friend(id = 3, name = "Виктор Кузнецов", imageUrl = ""))
            return friends
        }

        fun fillCategoriesStubData(): List<Category> {
            val categories = mutableListOf<Category>()
            categories.add(Category(id = 1, name = "Дети", image = R.drawable.icon_kids))
            categories.add(Category(id = 2, name = "Взрослые", image = R.drawable.icon_adult))
            categories.add(Category(id = 3, name = "Пожилые", image = R.drawable.icon_elderly))
            categories.add(Category(id = 4, name = "Животные", image = R.drawable.icon_animals))
            categories.add(Category(id = 5, name = "Мероприятия", image = R.drawable.icon_event))
            return categories
        }

        fun fillSearchResultsStubData(): List<SearchResult> {
            val results = mutableListOf<SearchResult>()
            val randomQuantity = (0..15).random()
            for (i in 0..randomQuantity) {
                results.add(SearchResult(title = faker.company().name()))
            }
            return results
        }
    }
}
