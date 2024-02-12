package com.ps_pn.firstblockpractice.kotlin_part.kotlin_part_1.task_1

/*
    Необходимо создать интерфейс Publication, у которого должно быть свойства – price и wordCount,
    а также метод getType, возвращающий строку. Создать два класса, реализующих данный интерфейс –
    Book и Magazine. В методе getType для класса Book возвращаем строку с зависимости от количества
    слов. Если количество слов не превышает 1000, то вывести “Flash Fiction”, 7,500 –“Short Story”,
    всё, что выше – “Novel”. Для класса Magazine возвращаем строку “Magazine”.
    */

fun main() {

    //  Создать два объекта класса Book и один объект Magazine.

    val book1 = Book(100, 5000)
    val book2 = Book(200, 8000)
    val magazine = Magazine(100000, 108000)

    //  Вывести в лог для каждого объекта тип, количество строк и цену в евро в отформатированном виде.

    println(book1)
    println(book2)
    println(magazine)

    /*
        У класса Book переопределить метод equals и произвести сравнение сначала по ссылке,
        затем используя метод equals.
        Результаты сравнений вывести в лог.
     */

    println(book1 === book2)
    println(book1 == book2)

    /*
       Создать метод buy, который в качестве параметра принимает Publication (notnull - значения)
       и выводит в лог “The purchase is complete. The purchase amount was [цена издания]”.
       Создать две переменных класса Book, в которых могут находиться null значения.
       Присвоить одной null, а второй любое notnull значение.
       Используя функцию let, попробуйте вызвать метод buy с каждой из переменных.
    */

    val book3: Book? = null
    val book4 = Book(400, 8000)

    book3?.let {
        buy(it)
    }
    book4?.let {
        buy(it)
    }

    /*
        Создать переменную sum и присвоить ей лямбда-выражение, которое будет складывать
        два переданных ей числа и выводить результат в лог. Вызвать данное лямбда-выражение с
        произвольными параметрами.
    */

    val sum = { a: Int, b: Int -> println(a + b) }

    sum.invoke(5, 13)
}

fun buy(publication: Publication) {
    val price = publication.price
    println("The purchase is complete. The purchase amount was $price")
}