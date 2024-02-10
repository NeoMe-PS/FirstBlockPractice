package com.ps_pn.firstblockpractice.collections;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see CollectionsBlockTest.
 */
public class CollectionsBlock<T extends Comparable> {

	/**
	 * Даны два упорядоченных по убыванию списка.
	 * Объедините их в новый упорядоченный по убыванию список.
	 * Исходные данные не проверяются на упорядоченность в рамках данного задания
	 *
	 * @param firstList  первый упорядоченный по убыванию список
	 * @param secondList второй упорядоченный по убыванию список
	 * @return объединенный упорядоченный список
	 * @throws NullPointerException если один из параметров null
	 */
	public List<T> collectionTask0(List<T> firstList, List<T> secondList) {

		if (firstList == null || secondList == null) {
			throw new NullPointerException();
		}
		List<T> result = new ArrayList<>();
		result.addAll(firstList);
		result.addAll(secondList);
		result.sort(Collections.reverseOrder());
		return result;


	}

	/**
	 * Дан список. После каждого элемента добавьте предшествующую ему часть списка.
	 *
	 * @param inputList с исходными данными
	 * @return измененный список
	 * @throws NullPointerException если один из параметров null
	 */
	public List<T> collectionTask1(List<T> inputList) {

		if (inputList == null) {
			throw new NullPointerException();
		}
		List<T> result = new ArrayList<>();
		for (int i = 0; i < inputList.size(); i++) {
			result.add(inputList.get(i));
			List<T> before = inputList.subList(0, i);
			result.addAll(before);
		}
		return result;
	}

	/**
	 * Даны два списка. Определите, совпадают ли множества их элементов.
	 *
	 * @param firstList  первый список элементов
	 * @param secondList второй список элементов
	 * @return <tt>true</tt> если множества списков совпадают
	 * @throws NullPointerException если один из параметров null
	 */
	public boolean collectionTask2(List<T> firstList, List<T> secondList) {

		if (firstList == null || secondList == null) {
			throw new NullPointerException();
		} else if (firstList.isEmpty() && secondList.isEmpty()) {
			return true;
		} else if (!firstList.isEmpty() && !secondList.isEmpty())
			if (firstList.size() > secondList.size()) {
				return firstList.containsAll(secondList);
			} else {
				return secondList.containsAll(firstList);
			}

		return false;
	}

	/**
	 * Создать список из заданного количества элементов.
	 * Выполнить циклический сдвиг этого списка на N элементов вправо или влево.
	 * Если N > 0 циклический сдвиг вправо.
	 * Если N < 0 циклический сдвиг влево.
	 *
	 * @param inputList список, для которого выполняется циклический сдвиг влево
	 * @param n         количество шагов циклического сдвига N
	 * @return список inputList после циклического сдвига
	 * @throws NullPointerException если один из параметров null
	 */
	public List<T> collectionTask3(List<T> inputList, int n) {

		if (inputList == null) {
			throw new NullPointerException();
		}
		if (inputList.isEmpty()) {
			return inputList;
		}
		Collections.rotate(inputList, n);
		return inputList;
	}

	/**
	 * Элементы списка хранят слова предложения.
	 * Замените каждое вхождение слова A на B.
	 *
	 * @param inputList список со словами предложения и пробелами для разделения слов
	 * @param a         слово, которое нужно заменить
	 * @param b         слово, на которое нужно заменить
	 * @return список после замены каждого вхождения слова A на слово В
	 * @throws NullPointerException если один из параметров null
	 */
	public List<String> collectionTask4(List<String> inputList, String a,
	                                    String b) {

		if (inputList == null || a == null || b == null) {
			throw new NullPointerException();
		}
		if (inputList.isEmpty()) {
			return inputList;
		}
		List<String> result = new ArrayList<>();
		for (String stroke : inputList) {
			String replaced = stroke.replace(a, b);
			result.add(replaced);
		}

		return result;
	}

    /*
      Задание подразумевает создание класса(ов) для выполнения задачи.

      Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
      курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
      Упорядочите студентов по курсу, причем студенты одного курса располагались
      в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
      Определите самого старшего студента и самого младшего студентов.
      Для каждой группы найдите лучшего с точки зрения успеваемости студента.
     */
}
