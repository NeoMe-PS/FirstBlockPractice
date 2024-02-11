package com.ps_pn.firstblockpractice.classes.tasks.first_task;

/*
      Создать класс с двумя переменными. Добавить функцию вывода на экран
      и функцию изменения этих переменных. Добавить функцию, которая находит
      сумму значений этих переменных, и функцию, которая находит наибольшее
      значение из этих двух переменных.
     */
public class SimpleClass {

	int x = 1;
	int y = 2;

	public void print() {
		System.out.println("x = " + x + " , y = " + y);
	}

	public int sum(int x, int y) {
		return x + y;
	}

	public int max(int x, int y) {
		return x > y ? x : y;
	}
}


