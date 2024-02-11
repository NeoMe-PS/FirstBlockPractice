package com.ps_pn.firstblockpractice.kotlin_part.kotlin_part_1.task_1

class Magazine(override var price: Int, override var wordCount: Int) : Publication {
    override fun getType(): String {
       return "Magazine"
    }

    override fun toString(): String {
        return "Magazine: \nPrice : €$price \nWord count : $wordCount \nType : ${this.getType()}"
    }

}