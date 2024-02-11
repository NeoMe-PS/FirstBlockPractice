package com.ps_pn.firstblockpractice.classes.tasks.fourth_task;

/*
	 IV

	 Составить описание класса для представления времени.
	 Предусмотреть возможности установки времени и изменения его отдельных полей
	 (час, минута, секунда) с проверкой допустимости вводимых значений.
	 В случае недопустимых значений полей выбрасываются исключения.
	 Создать методы изменения времени на заданное количество часов, минут и секунд.
	*/
public class Time {
	private int hours = 0;
	private int minutes = 0;
	private int seconds = 0;

	private final int MIN_TIME_VALUE = 1;
	private final int MAX_TIME_VALUE = 60;
	private final int MAX_HOUR_VALUE = 24;

	public Time(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	public Time(int hours) {
		this.hours = hours;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) throws Exception {
		if (!isCorrectHour(hours)) {
			throw new Exception("Illegal argument value hours : " + hours + " Must be from 0 to 23");
		}
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) throws Exception {
		if (!isCorrectValue(minutes)) {
			throw new Exception("Illegal argument value minutes : " + minutes + " Must be from 0 to 59");
		}
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) throws Exception {
		if (!isCorrectValue(seconds)) {
			throw new Exception("Illegal argument value seconds : " + seconds + " Must be from 0 to 59");
		}
		this.seconds = seconds;
	}

	private boolean isCorrectValue(int value) {
		return value >= MIN_TIME_VALUE && value <= MAX_TIME_VALUE;
	}

	private boolean isCorrectHour(int value) {
		return value >= MIN_TIME_VALUE && value < MAX_HOUR_VALUE;
	}

	public void addHourToTime(int hour) throws Exception {
		if (!isCorrectHour(hour)) {
			throw new Exception("Illegal argument value hours : " + hours + " Must be from 0 to 23");
		}
		int summaryHour = hours + hour;
		if (summaryHour == MAX_HOUR_VALUE) {
			hours = 0;
			return;
		}
		if (summaryHour > MAX_HOUR_VALUE) {
			hours = summaryHour - MAX_HOUR_VALUE;
			return;
		}
		hours = summaryHour;
	}

	public void addMinutesToTime(int minut) throws Exception {
		if (!isCorrectValue(minut)) {
			throw new Exception("Illegal argument value minutes : " + minut + " Must be from 0 to 59");
		}
		int summaryMinutes = minutes + minut;
		if (summaryMinutes > 59) {
			addHourToTime(1);
			minutes = summaryMinutes - 60;
			return;
		}
		minutes = summaryMinutes;
	}

	public void addSecondsToTime(int sec) throws Exception {
		if (!isCorrectValue(sec)) {
			throw new Exception("Illegal argument value seconds : " + sec + " Must be from 0 to 60");
		}
		int summarySeconds = seconds + sec;
		if (summarySeconds > 59) {
			addMinutesToTime(1);
			seconds = summarySeconds - 60;
			return;
		}
		seconds = summarySeconds;
	}

	@Override
	public String toString() {
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}
}
