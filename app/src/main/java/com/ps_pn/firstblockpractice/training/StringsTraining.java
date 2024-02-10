package com.ps_pn.firstblockpractice.training;

import java.util.ArrayList;
import java.util.List;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see StringsTrainingTest.
 */
public class StringsTraining {

	/**
	 * Метод по созданию строки,
	 * состоящей из нечетных символов
	 * входной строки в том же порядке
	 * (нумерация символов идет с нуля)
	 *
	 * @param text строка для выборки
	 * @return новая строка из нечетных
	 * элементов строки text
	 */
	public String getOddCharacterString(String text) {
		if (text.isEmpty() || text.length() == 1) {
			return "";
		}
		char[] charArray = text.toCharArray();
		StringBuilder result = new StringBuilder();
		for (int i = 1; i < charArray.length; i += 2) {
			result.append(charArray[i]);
		}
		return result.toString();
	}

	/**
	 * Метод для определения количества
	 * символов, идентичных последнему
	 * в данной строке
	 *
	 * @param text строка для выборки
	 * @return массив с номерами символов,
	 * идентичных последнему. Если таких нет,
	 * вернуть пустой массив
	 */
	public int[] getArrayLastSymbol(String text) {

		if (text.isEmpty() || text.length() == 1) {
			return new int[]{};
		}

		List<Integer> indexes = new ArrayList<>();
		char lastSymbol = text.charAt(text.length() - 1);
		for (int i = 0; i < text.length() - 1; i++) {
			if (text.charAt(i) == lastSymbol) {
				indexes.add(i);
			}
		}

		return indexes.stream().mapToInt(Integer::intValue).toArray();
	}

	/**
	 * Метод по получению количества
	 * цифр в строке
	 *
	 * @param text строка для выборки
	 * @return количество цифр в строке
	 */
	public int getNumbersCount(String text) {

		if (text != null) {
			return text.replaceAll("\\D", "").length();
		} else {
			return 0;
		}
	}

	/**
	 * Дан текст. Заменить все цифры
	 * соответствующими словами.
	 *
	 * @param text текст для поиска и замены
	 * @return текст, где цифры заменены словами
	 */
	public String replaceAllNumbers(String text) {

		if (text != null) {
			return text.replaceAll("1", "one")
					.replaceAll("2", "two")
					.replaceAll("3", "three")
					.replaceAll("4", "four")
					.replaceAll("5", "five")
					.replaceAll("6", "six")
					.replaceAll("7", "seven")
					.replaceAll("8", "eight")
					.replaceAll("9", "nine")
					.replaceAll("0", "zero");
		} else {
			return null;
		}

	}

	/**
	 * Метод должен заменить заглавные буквы
	 * на прописные, а прописные на заглавные
	 *
	 * @param text строка для изменения
	 * @return измененная строка
	 */
	public String capitalReverse(String text) {

		if (text == null) {
			return null;
		}

		char[] chars = text.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (Character.isUpperCase(c)) {
				chars[i] = Character.toLowerCase(c);
			} else if (Character.isLowerCase(c)) {
				chars[i] = Character.toUpperCase(c);
			}
		}
		return String.valueOf(chars);
	}

}
