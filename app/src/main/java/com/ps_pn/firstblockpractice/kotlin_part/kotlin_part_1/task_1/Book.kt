package com.ps_pn.firstblockpractice.kotlin_part.kotlin_part_1.task_1

class Book(override var price: Int, override var wordCount: Int) : Publication {
    override fun getType(): String {
        if (wordCount < 1000) {
            return "Flash Fiction"
        }
        if (wordCount < 7500) {
            return "Short Story"
        }
        return "Novel"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (price != other.price) return false
        return wordCount == other.wordCount
    }

    override fun hashCode(): Int {
        var result = price
        result = 31 * result + wordCount
        return result
    }

    override fun toString(): String {
        return "Book: \nPrice : €$price \nWord count : $wordCount \nType : ${this.getType()}"
    }


}