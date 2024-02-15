package com.ps_pn.firstblockpractice.kotlin_part_2

/*  11. Реализовать изолированный класс Action и его наследников – Registration, Login и Logout.
        Login должен принимать в качестве параметра экземпляр класса User. */

sealed class Action {
    object Registration : Action()
    class Login(user: User) : Action()
    object Logout : Action()

}