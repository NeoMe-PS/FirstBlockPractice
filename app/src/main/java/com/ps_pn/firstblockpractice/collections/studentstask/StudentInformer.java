package com.ps_pn.firstblockpractice.collections.studentstask;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class StudentInformer {

	public HashMap<SchoolSubjects, Double> getAvgByGroup(List<Student> students, int group) {

		List<Student> studentsInCourse = students.stream()
				.filter(student -> student.getNumOfGroup() == group)
				.collect(Collectors.toList());

		HashMap<SchoolSubjects, Double> result = new HashMap<>();
		HashMap<SchoolSubjects, List<Integer>> mapWithAllMarks = new HashMap<>();

		for (SchoolSubjects subject : SchoolSubjects.values()) {
			mapWithAllMarks.put(subject, new ArrayList<>());
		}

		for (int i = 0; i < studentsInCourse.size(); i++) {

			HashMap<SchoolSubjects, List<Integer>> studentMap = studentsInCourse.get(i)
					.getSchoolGrades();
			// добавление всех оценок студентов по каждому предмету
			for (Map.Entry<SchoolSubjects, List<Integer>> entry : studentMap.entrySet()) {
				mapWithAllMarks.get(entry.getKey()).addAll(entry.getValue());
			}
		}
			// вычисление avg по каждому предмету
		for (Map.Entry<SchoolSubjects, List<Integer>> entry : mapWithAllMarks.entrySet()) {
			double avg = entry.getValue().stream().mapToInt(Integer::intValue)
					.average()
					.getAsDouble();
			result.put(entry.getKey(), avg);
		}
		return result;
	}


	public Student getOldestStudent(List<Student> students) {
		return students
				.stream()
				.min(Comparator.comparing(Student::getYearOfBirthday))
				.orElseThrow(NoSuchElementException::new);
	}

	public Student getYoungesStudent(List<Student> students) {
		return students
				.stream()
				.max(Comparator.comparing(Student::getYearOfBirthday))
				.orElseThrow(NoSuchElementException::new);
	}


	public Student getBestStudentByGroup(List<Student> students, int group) {

		// Список студентов одной группы
		List<Student> studentsInCourse = students.stream()
				.filter(student -> student.getNumOfGroup() == group)
				.collect(Collectors.toList());

		Student bestStudent = studentsInCourse.get(0);
		double bestStudentMark = 0;

		// Цикл в котором получаем журнал оценок студента и вычисляем по нему avg всех оценок
		for (int i = 0; i < studentsInCourse.size(); i++) {

			Student currentStudent = studentsInCourse.get(i);

			HashMap<SchoolSubjects, List<Integer>> currentStudentGradeMap = currentStudent
					.getSchoolGrades();

			List<Integer> marksByOneStudent = new ArrayList<>();

			// Получение списка средних оценок по всем предметам
			for (Map.Entry<SchoolSubjects, List<Integer>> entry : currentStudentGradeMap.entrySet()) {
				marksByOneStudent.addAll(entry.getValue());
			}
			// Получение средней оценки исходя из всех
			double thisStudentMark = marksByOneStudent.stream()
					.mapToDouble(Integer::intValue)
					.average()
					.getAsDouble();

			if (bestStudentMark < thisStudentMark) {
				bestStudent = currentStudent;
				bestStudentMark = thisStudentMark;
			}
			marksByOneStudent.clear();
		}
		return bestStudent;
	}
}
