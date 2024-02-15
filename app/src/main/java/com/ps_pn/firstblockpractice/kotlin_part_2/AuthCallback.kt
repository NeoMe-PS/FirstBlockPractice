package com.ps_pn.firstblockpractice.kotlin_part_2

    /*  8. Создать интерфейс AuthCallback с методами authSuccess, authFailed и
           реализовать анонимный объект данного интерфейса.
           В методах необходимо вывести в лог информацию о статусе авторизации.*/

interface AuthCallback {
    fun authSuccess()
    fun authFailed()

}
val callback = object : AuthCallback {
    override fun authSuccess() {
        println("Success")
    }

    override fun authFailed() {
        println("Fail")
    }
}