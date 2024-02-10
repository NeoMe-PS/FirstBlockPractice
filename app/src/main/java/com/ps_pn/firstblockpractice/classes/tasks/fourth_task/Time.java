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
		if (isCorrectHour(hours)) {
			this.hours = hours;
		} else {
			throw new Exception("Illegal argument value hours : " + hours + " Must be from 0 to 23");
		}
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) throws Exception {
		if (isCorrectValue(minutes)) {
			this.minutes = minutes;
		} else {
			throw new Exception("Illegal argument value minutes : " + minutes + " Must be from 0 to 59");
		}
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) throws Exception {
		if (isCorrectValue(seconds)) {
			this.seconds = seconds;
		} else {
			throw new Exception("Illegal argument value seconds : " + seconds + " Must be from 0 to 59");
		}
	}

	private boolean isCorrectValue(int value) {
		return value >= 1 && value <= 60;
	}

	private boolean isCorrectHour(int value) {
		return value >= 1 && value < 24;
	}

	public void addHourToTime(int hour) throws Exception {
		if (isCorrectHour(hour)) {
			int summaryHour = hours + hour;
			if (summaryHour == 24) {
				hours = 0;
			} else if (summaryHour > 24) {
				hours = summaryHour - 24;
			} else {
				hours = summaryHour;
			}
		} else {
			throw new Exception("Illegal argument value hours : " + hours + " Must be from 0 to 23");
		}
	}

	public void addMinutesToTime(int minut) throws Exception {
		if (isCorrectValue(minut)) {
			int summaryMinutes = minutes + minut;
			if (summaryMinutes > 59) {
				addHourToTime(1);
				minutes = summaryMinutes - 60;
			} else {
				minutes = summaryMinutes;
			}
		} else {
			throw new Exception("Illegal argument value minutes : " + minut + " Must be from 0 to 59");
		}
	}

	public void addSecondsToTime(int sec) throws Exception {
		if (isCorrectValue(sec)) {
			int summarySeconds = seconds + sec;
			if (summarySeconds > 59) {
				addMinutesToTime(1);
				seconds = summarySeconds - 60;
			} else {
				seconds = summarySeconds;
			}
		} else {
			throw new Exception("Illegal argument value seconds : " + sec + " Must be from 0 to 60");
		}
	}

	@Override
	public String toString() {
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

}
