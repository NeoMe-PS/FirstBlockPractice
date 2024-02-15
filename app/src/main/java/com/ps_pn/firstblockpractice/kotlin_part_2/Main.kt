package com.ps_pn.firstblockpractice.kotlin_part_2

fun main() {

    /*  3. Создать объект класса User, вывести в лог startTime данного юзера,
           после вызвать Thread.sleep(1000) и повторно вывести в лог startTime. */

    val user = User(1, "Igor", 55, Type.FULL)

    println(user.startTime)
    Thread.sleep(1000)
    println(user.startTime)

    /*  4. Создать список пользователей, содержащий в себе один объект класса User.
           Используя функцию apply, добавить ещё несколько объектов класса User в список пользователей.*/

    val users = mutableListOf(user).apply {
        add(User(2, "Gosha", 23, Type.FULL))
        add(User(3, "Arkadii", 65, Type.DEMO))
    }

    /*  5. Получить список пользователей, у которых имеется полный доступ
           (поле type имеет значение FULL). */

    val usersWithAccess = users.filter { it.type == Type.FULL }

    /*  6. Преобразовать список User в список имен пользователей.
           Получить первый и последний элементы списка и вывести их в лог. */

    val names = users.map { it.name }

    println(names.first())
    println(names.last())

}

/*  7. Создать функцию-расширение класса User, которая проверяет, что юзер старше 18 лет,
       и в случае успеха выводит в лог, а в случае неуспеха возвращает ошибку. */

class UserAgeException : Exception()

fun User.isAdult() {
    if (age < ADULT_AGE) {
        throw UserAgeException()
    }
    println("$name is adult")
}

/*  9. Реализовать inline функцию auth, принимающую в качестве параметра функцию updateCache.
       Функция updateCache должна выводить в лог информацию об обновлении кэша.
   10. Внутри функции auth вызвать метод коллбека authSuccess и переданный updateCache,
       если проверка возраста пользователя произошла без ошибки.
       В случае получения ошибки вызвать authFailed. */

inline fun auth(callback: AuthCallback, user: User, updateCache: () -> Unit) {
    try {
        user.isAdult()
        callback.authSuccess()
        updateCache.invoke()
    } catch (e: UserAgeException) {
        callback.authFailed()
    }
}

fun updateCache() {
    println("Cache updated")
}

/*  12. Реализовать метод doAction, принимающий экземпляр класса Action.
        В зависимости от переданного действия выводить в лог текст,
        к примеру “Auth started”. Для действия Login вызывать метод auth. */

fun doAction(action: Action, user: User, callback: AuthCallback) {
    when (action) {
        is Action.Login -> auth(callback, user) { updateCache() }
        is Action.Logout -> println("Logout")
        is Action.Registration -> println("Registration")
    }
}