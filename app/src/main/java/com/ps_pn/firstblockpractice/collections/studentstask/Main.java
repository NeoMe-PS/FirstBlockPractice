package com.ps_pn.firstblockpractice.collections.studentstask;

import com.ps_pn.firstblockpractice.collections.studentstask.comporators.StudentCourseComporator;
import com.ps_pn.firstblockpractice.collections.studentstask.comporators.StudentFamilyComporator;

import java.util.List;

/*
      Задание подразумевает создание класса(ов) для выполнения задачи.

      Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
      курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
      Упорядочите студентов по курсу, причем студенты одного курса располагались
      в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
      Определите самого старшего студента и самого младшего студентов.
      Для каждой группы найдите лучшего с точки зрения успеваемости студента.
     */

public class Main {

	public static void main(String[] args) {

		TaskInitializer initializer = new TaskInitializer();
		List<Student> students = initializer.getStudents();
		StudentInformer informer = new StudentInformer();

		students.sort(new StudentCourseComporator().thenComparing(new StudentFamilyComporator()));

		System.out.println(students);
		separateText();
		System.out.println("Средняя оценка группы: 1");
		System.out.println(informer.getAvgByGroup(students,1));
		separateText();
		System.out.println("Средняя оценка группы: 2");
		System.out.println(informer.getAvgByGroup(students,2));
		separateText();
		System.out.println("Самый старый студент:");
		System.out.println(informer.getOldestStudent(students));
		separateText();
		System.out.println("Самый молодой студент:");
		System.out.println(informer.getYoungesStudent(students));
		separateText();
		System.out.println("Лучший студент группы: 1");
		System.out.println(informer.getBestStudentByGroup(students,1));
		separateText();
		System.out.println("Лучший студент группы: 2");
		System.out.println(informer.getBestStudentByGroup(students,2));
	}

	private static void separateText(){
		System.out.println("-------------");
	}
}