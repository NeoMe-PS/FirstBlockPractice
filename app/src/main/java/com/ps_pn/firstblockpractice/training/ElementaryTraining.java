package com.ps_pn.firstblockpractice.training;

/**
 * Набор тренингов по работе с примитивными типами java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ElementaryTrainingTest.
 */
public class ElementaryTraining {

	/**
	 * Метод должен возвращать среднее значение
	 * для введенных параметров
	 *
	 * @param firstValue  первый элемент
	 * @param secondValue второй элемент
	 * @return среднее значение для введенных чисел
	 */
	public double averageValue(int firstValue, int secondValue) {

		double result = (double) (firstValue + secondValue) / 2;
		return result;
	}

	/**
	 * Пользователь вводит три числа.
	 * Произвести манипуляции и вернуть сумму новых чисел
	 *
	 * @param firstValue  увеличить в два раза
	 * @param secondValue уменьшить на три
	 * @param thirdValue  возвести в квадрат
	 * @return сумма новых трех чисел
	 */
	public double complicatedAmount(int firstValue, int secondValue, int thirdValue) {

		firstValue += firstValue;
		secondValue -= 3;
		thirdValue *= thirdValue;
		double result = firstValue + secondValue + thirdValue;
		return result;
	}

	/**
	 * Метод должен поменять значение в соответствии с условием.
	 * Если значение больше 3, то увеличить
	 * на 10, иначе уменьшить на 10.
	 *
	 * @param value число для изменения
	 * @return новое значение
	 */
	public int changeValue(int value) {

		if (value > 3) {
			value += 10;
		} else {
			value -= 10;
		}

		return value;
	}

	/**
	 * Метод должен менять местами первую
	 * и последнюю цифру числа.
	 * Обрабатывать максимум пятизначное число.
	 * Если число < 10, вернуть
	 * то же число
	 *
	 * @param value число для перестановки
	 * @return новое число
	 */
	public int swapNumbers(int value) {

		int maxAllowedNum = 99999;
		int minAllowedNum = 10;

		if ((value < minAllowedNum) || (value > maxAllowedNum)) {
			return value;
		} else {
			String valueAsText = String.valueOf(value);
			valueAsText = valueAsText.substring(valueAsText.length() - 1)
					+ valueAsText.substring(1, valueAsText.length() - 1)
					+ valueAsText.charAt(0);
			value = Integer.parseInt(valueAsText);
		}

		return value;
	}

	/**
	 * Изменить значение четных цифр числа на ноль.
	 * Счет начинать с единицы.
	 * Обрабатывать максимум пятизначное число.
	 * Если число < 10 вернуть
	 * то же число.
	 *
	 * @param value число для изменения
	 * @return новое число
	 */
	public int zeroEvenNumber(int value) {

		int maxAllowedNum = 99999;
		int minAllowedNum = 10;
		int result;

		if ((value < minAllowedNum) || (value > maxAllowedNum)) {
			return value;
		} else {
			String valueAsString = Integer.toString(value);
			int length = valueAsString.length();
			StringBuilder stringResult = new StringBuilder();

			for (int i = 0; i < length; i++) {

				int digit = Integer.parseInt(String.valueOf(valueAsString.charAt(i)));
				if (digit % 2 == 0) {
					stringResult.append(0);
				} else {
					stringResult.append(valueAsString.charAt(i));
				}
			}
			result = Integer.parseInt(String.valueOf(stringResult));
		}
		return result;
	}
}
