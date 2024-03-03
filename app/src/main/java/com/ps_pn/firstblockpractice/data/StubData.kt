package com.ps_pn.firstblockpractice.data

import com.github.javafaker.Faker
import com.ps_pn.firstblockpractice.R
import com.ps_pn.firstblockpractice.presentation.adapter.Category
import com.ps_pn.firstblockpractice.presentation.adapter.friend.Friend
import com.ps_pn.firstblockpractice.presentation.adapter.help.CategoryHelpEntity
import com.ps_pn.firstblockpractice.presentation.adapter.news.News
import com.ps_pn.firstblockpractice.presentation.adapter.search.SearchResultEntity

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

        fun fillCategoriesStubData(): List<CategoryHelpEntity> {
            val categories = mutableListOf<CategoryHelpEntity>()
            categories.add(
                CategoryHelpEntity(
                    category = Category.KIDS,
                    image = R.drawable.icon_kids
                )
            )
            categories.add(
                CategoryHelpEntity(
                    category = Category.ADULT,
                    image = R.drawable.icon_adult
                )
            )
            categories.add(
                CategoryHelpEntity(
                    category = Category.ELDERLY,
                    image = R.drawable.icon_elderly
                )
            )
            categories.add(
                CategoryHelpEntity(
                    category = Category.ANIMALS,
                    image = R.drawable.icon_animals
                )
            )
            categories.add(
                CategoryHelpEntity(
                    category = Category.EVENT,
                    image = R.drawable.icon_event
                )
            )
            return categories
        }

        fun fillSearchResultsStubData(): List<SearchResultEntity> {
            val results = mutableListOf<SearchResultEntity>()
            val randomQuantity = (0..15).random()
            for (i in 0..randomQuantity) {
                results.add(SearchResultEntity(title = faker.company().name()))
            }
            return results
        }

        fun fillNewsStubData(): List<News> {
            val news = mutableListOf<News>()
            news.add(
                News(
                    id = 1,
                    category = Category.KIDS,
                    label = faker.harryPotter().character(),
                    shortDesc = faker.harryPotter().quote(),
                    date = faker.date().birthday().toString(),
                    image = R.drawable.img_2
                )
            )
            news.add(
                News(
                    id = 2,
                    category = Category.KIDS,
                    label = faker.harryPotter().character(),
                    shortDesc = faker.harryPotter().quote(),
                    date = faker.date().birthday().toString(),
                    image = R.drawable.img
                )
            )
            return news
        }
    }
}
