package com.ps_pn.firstblockpractice.classes.tasks.second_task;

import java.util.Arrays;
import java.util.Random;

/*
	 II

	 Создать класс, содержащий динамический массив и количество элементов в нем.
	 Добавить конструктор, который выделяет память под заданное количество элементов.
	 Добавить методы, позволяющие заполнять массив случайными числами,
	 переставлять в данном массиве элементы в случайном порядке, находить количество
	 различных элементов в массиве, выводить массив на экран.
	*/

public class Array<T> {

	private static final int DEFAULT_CAPACITY = 10;
	private Object array[];
	private int capacity = DEFAULT_CAPACITY;
	private int size = 0;


	public Object[] getArray() {
		return array;
	}


	public int getCapacity() {
		return capacity;
	}


	public int getSize() {
		return size;
	}


	public Array(int capacity) {
		if (capacity > 0) {
			this.capacity = capacity;
			this.array = new Object[capacity];
		} else {
			this.array = new Object[DEFAULT_CAPACITY];
		}
	}

	public Array() {
		this.array = new Object[DEFAULT_CAPACITY];
	}

	private Object[] grow() {
		int oldCapacity = array.length;
		int newCapacity = oldCapacity + oldCapacity;
		return Arrays.copyOf(array, newCapacity);

	}

	public void add(T element) {
		if (isFull()) {
			array = grow();
		}
		array[size] = element;
		size++;

	}

	public T get(int index) {
		isCorrectIndex(index);
		return (T) array[index];

	}

	public void shuffle() {
		for (int i = 0; i < size; i++) {
			int randomIndex = new Random().nextInt(size);
			T randElement = (T) array[randomIndex];
			array[randomIndex] = array[i];
			array[i] = randElement;
		}
	}

	public int getCountsOfElement(T element) {
		int counter = 0;
		for (int i = 0; i < size; i++) {
			if (element.equals(array[i])) {
				counter++;
			}
		}
		return counter;
	}


	private void isCorrectIndex(int index) {
		if (index < 0 || index >= array.length) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	private boolean isFull() {
		return size + 1 >= array.length;
	}


	public void fillRandomInt(int countOfElements) {

		for (int i = 0; i < countOfElements; i++) {
			Integer random = (int) (Math.random() * 10);
			add((T) random);

		}
	}

	public void printArray() {
		System.out.print("Array elements: ");
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				System.out.print(this.array[i]);
			} else {
				System.out.print(this.array[i] + ", ");
			}
		}
		System.out.println();
	}


	public static void main(String[] args) {

		Array<Integer> array = new Array<>();
		Array<Integer> array2 = new Array<>();
		array2.add(44);
		array2.add(44);
		array2.add(33);

		System.out.println("Array fillRandom:");
		separateText();
		array.fillRandomInt(10);
		array.printArray();
		separateText();

		System.out.println("Array add:");
		array.add(54);
		array.printArray();
		separateText();

		System.out.println("Array get:");
		System.out.println(array.get(4));
		separateText();

		System.out.println("Array shuffle:");
		array.shuffle();
		array.printArray();
		separateText();

		System.out.println("Array getCounts:");

		System.out.println(array2.getCountsOfElement(33));
		separateText();
	}

	private static void separateText() {
		System.out.println("-------------");
	}
}
