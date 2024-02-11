package com.ps_pn.firstblockpractice.classes.tasks.sixth_task;

import com.ps_pn.firstblockpractice.classes.tasks.sixth_task.entity.Enrollee;
import com.ps_pn.firstblockpractice.classes.tasks.sixth_task.entity.EntranceExamsSystem;
import com.ps_pn.firstblockpractice.classes.tasks.sixth_task.entity.Faculty;
import com.ps_pn.firstblockpractice.classes.tasks.sixth_task.entity.Teacher;

import java.util.List;

/*
      VI

      Задача на взаимодействие между классами. Разработать систему «Вступительные экзамены».
      Абитуриент регистрируется на Факультет, сдает Экзамены. Преподаватель выставляет Оценку.
      Система подсчитывает средний бал и определяет Абитуриента, зачисленного в учебное заведение.
     */

public class Main {

	public static void main(String[] args) throws Exception {

		List<Enrollee> enrollees = Utill.getEnrolleList();
		Teacher teacher = new Teacher();
		Faculty dobroFaculty = new Faculty("Faculty of fighting evil forces", Faculty.DOBRO_TYPE, 4);
		Faculty zloFaculty = new Faculty("Faculty of mortal ass-kicking", Faculty.ZLO_TYPE, 2);
		EntranceExamsSystem EntranceSystem = new EntranceExamsSystem();

		EntranceSystem.registerEnrolleeInFaculty(enrollees, dobroFaculty, zloFaculty);

		EntranceSystem.enrollessPassExams(enrollees);

		EntranceSystem.makeDecisionOnAdmission(enrollees, teacher);

		System.out.println(EntranceSystem.getEnteredTheFaculty());
		System.out.println(dobroFaculty);
		System.out.println(zloFaculty);
	}
}