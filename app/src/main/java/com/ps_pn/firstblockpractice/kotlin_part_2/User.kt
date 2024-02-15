package com.ps_pn.firstblockpractice.kotlin_part_2

import java.util.Calendar

/*  2. Реализовать класс данных User с полями id, name, age и type.
       У класса User создать ленивое свойство startTime, в котором получаем текущее время.*/

data class User(
    val id: Int,
    val name: String,
    var age: Int,
    val type: Type
) {

    val ADULT_AGE = 18

    val startTime by lazy {
        "${Calendar.getInstance().time}"
    }
}