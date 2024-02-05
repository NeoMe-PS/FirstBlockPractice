package com.ps_pn.firstblockpractice.training;

/**
 * Набор тренингов по работе с массивами в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ArraysTrainingTest.
 */
public class ArraysTraining {

	/**
	 * Метод должен сортировать входящий массив
	 * по возрастранию пузырьковым методом
	 *
	 * @param valuesArray массив для сортировки
	 * @return отсортированный массив
	 */
	public int[] sort(int[] valuesArray) {

		int arrayLength = valuesArray.length - 1;
		for (int i = 0; i < arrayLength; i++) {
			for (int j = arrayLength; j > i; j--) {
				if (valuesArray[j - 1] > valuesArray[j]) {
					int tmp = valuesArray[j - 1];
					valuesArray[j - 1] = valuesArray[j];
					valuesArray[j] = tmp;
				}
			}
		}
		return valuesArray;
	}

	/**
	 * Метод должен возвращать максимальное
	 * значение из введенных. Если входящие числа
	 * отсутствуют - вернуть 0
	 *
	 * @param values входящие числа
	 * @return максимальное число или 0
	 */
	public int maxValue(int... values) {
		if (values.length == 0) {
			return 0;
		}
		sort(values);
		return values[values.length - 1];
	}

	/**
	 * Переставить элементы массива
	 * в обратном порядке
	 *
	 * @param array массив для преобразования
	 * @return входящий массив в обратном порядке
	 */
	public int[] reverse(int[] array) {

		for (int i = 0; i < array.length / 2; i++) {
			int temp = array[i];
			array[i] = array[array.length - i - 1];
			array[array.length - i - 1] = temp;
		}
		return array;
	}

	/**
	 * Метод должен вернуть массив,
	 * состоящий из чисел Фибоначчи
	 *
	 * @param numbersCount количество чисел Фибоначчи,
	 *                     требуемое в исходящем массиве.
	 *                     Если numbersCount < 1, исходный
	 *                     массив должен быть пуст.
	 * @return массив из чисел Фибоначчи
	 */
	public int[] fibonacciNumbers(int numbersCount) {

		if (numbersCount < 1) {
			return new int[]{};
		}
		int[] result = new int[numbersCount];
		if (numbersCount == 1) {
			return new int[]{1};
		}
		result[0] = 1;
		result[1] = 1;
		for (int i = 2; i < numbersCount; ++i) {
			result[i] = result[i - 1] + result[i - 2];
		}
		return result;
	}

	/**
	 * В данном массиве найти максимальное
	 * количество одинаковых элементов.
	 *
	 * @param array массив для выборки
	 * @return количество максимально встречающихся
	 * элементов
	 */
	public int maxCountSymbol(int[] array) {
		if (array.length == 0) {
			return 0;
		}
		int counter = 1;
		int result = 1;
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] == array[j]) {
					counter++;
				}
			}
			if (result < counter) {
				result = counter;
			}
			counter = 1;
		}
		return result;
	}
}
