package com.ps_pn.firstblockpractice.classes.tasks.fifth_task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
	 V

	 Класс Абонент: Идентификационный номер, Фамилия, Имя, Отчество, Адрес,
	 Номер кредитной карточки, Дебет, Кредит, Время междугородных и городских переговоров;
	 Конструктор; Методы: установка значений атрибутов, получение значений атрибутов,
	 вывод информации. Создать массив объектов данного класса.
	 Вывести сведения относительно абонентов, у которых время городских переговоров
	 превышает заданное.  Сведения относительно абонентов, которые пользовались
	 междугородной связью. Список абонентов в алфавитном порядке.
	*/

public class Abonent implements Comparable<Abonent> {

	private int id;
	private String family;
	private String name;
	private String surname;
	private String address;
	private int creditCard;
	private int debit;
	private int credit;
	private int interCityTime;
	private int cityTime;

	public Abonent(
			int id,
			String family,
			String name,
			String surname,
			String address,
			int creditCard,
			int debit,
			int credit,
			int interCityTime,
			int cityTime
	) {
		this.id = id;
		this.family = family;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.creditCard = creditCard;
		this.debit = debit;
		this.credit = credit;
		this.interCityTime = interCityTime;
		this.cityTime = cityTime;
	}

	public static List<Abonent> getAbonCityTimeMoreThen(int sec, List<Abonent> abonents) {
		List<Abonent> result = new ArrayList<>();
		for (int i = 0; i < abonents.size(); i++) {
			if (abonents.get(i).cityTime > sec) {
				result.add(abonents.get(i));
			}
		}
		return result;
	}

	public static List<Abonent> getAbonentsWithInterCityTime(List<Abonent> abonents) {
		List<Abonent> result = new ArrayList<>();
		for (int i = 0; i < abonents.size(); i++) {
			if (abonents.get(i).interCityTime > 0) {
				result.add(abonents.get(i));
			}
		}
		return result;
	}

	public static void sortAbonents(List<Abonent> abonents) {
		Collections.sort(abonents);
	}

	@Override
	public String toString() {
		return "Abonent{" +
				"id=" + id +
				", family='" + family + '\'' +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", address='" + address + '\'' +
				", creditCard=" + creditCard +
				", debit=" + debit +
				", credit=" + credit +
				", interCityTime=" + interCityTime +
				", cityTime=" + cityTime +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Abonent o) {
		return this.family.compareTo(o.family);
	}

	public static void main(String[] args) {
		List<Abonent> abonents = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			abonents.add(new Abonent(
					i,
					"Family " + i,
					"Name" + i,
					"surname" + i,
					"Addres" + i,
					new Random().nextInt(),
					new Random().nextInt(),
					new Random().nextInt(),
					new Random().nextInt(50),
					new Random().nextInt(50)
			));
		}

		// Вывести абонентов
		System.out.println(abonents);

		// Список абонентов в алфавитном порядке
		Abonent.sortAbonents(abonents);
		System.out.println(abonents);

		 /*Вывести сведения относительно абонентов, у которых время городских переговоров
				превышает заданное.*/
		System.out.println(Abonent.getAbonCityTimeMoreThen(34, abonents));

		// Сведения относительно абонентов, которые пользовались междугородной связью

		System.out.println(Abonent.getAbonentsWithInterCityTime(abonents));

	}
}

