package com.ps_pn.firstblockpractice.data

import com.ps_pn.firstblockpractice.presentation.adapter.friend.Friend

class StubData {
    companion object{
        fun fillStubData(): List<Friend> {
            val friends = mutableListOf<Friend>()
            friends.add(Friend(id = 1, name = "Дмитрий Валерьевич", imageUrl = ""))
            friends.add(Friend(id = 2, name = "Евгений Александров", imageUrl = ""))
            friends.add(Friend(id = 3, name = "Виктор Кузнецов", imageUrl = ""))
            return friends
        }
    }
}
