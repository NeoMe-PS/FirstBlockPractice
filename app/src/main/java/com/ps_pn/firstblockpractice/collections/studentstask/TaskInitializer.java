package com.ps_pn.firstblockpractice.collections.studentstask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskInitializer {

	private List<String> pullOfNames = new ArrayList<>();
	private List<String> pullOfSurnames = new ArrayList<>();
	private List<String> pullOfFamilies = new ArrayList<>();

	public static int getYear() {
		int min = 2005;
		int max = 2009;
		max -= min;
		return (int) (Math.random() * ++max) + min;
	}
	{
		fillPullOfFamilies();
		fillPullOfNames();
		fillPullOfSurnames();
	}

	private void fillPullOfNames() {
		pullOfNames.add("John");
		pullOfNames.add("Konstantin");
		pullOfNames.add("Dmitri");
		pullOfNames.add("Dobrynya");
		pullOfNames.add("Vladimir");
		pullOfNames.add("Evpatii");
	}

	private void fillPullOfSurnames() {
		pullOfSurnames.add("Vladimirovich");
		pullOfSurnames.add("Nikolaevich");
		pullOfSurnames.add("Kolovratovich");
		pullOfSurnames.add("Magomedovich");
		pullOfSurnames.add("Sergeevich");
		pullOfSurnames.add("Mstislavovich");
	}

	private void fillPullOfFamilies() {
		pullOfFamilies.add("Putin");
		pullOfFamilies.add("Donskoi");
		pullOfFamilies.add("Mudrii");
		pullOfFamilies.add("Polockii");
		pullOfFamilies.add("Groznii");
		pullOfFamilies.add("Romanov");
	}

	public List<Student> getStudents() {
		List<Student> students = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			students.add(getStudent());
		}
		return students;
	}

	private Student getStudent() {
		return new Student(
				getRandomFamily(),
				getRandomName(),
				getRandomSurname(),
				getYear(),
				getRandomBeforeFive(),
				getRandomGroup(),
				getSchoolGrades()
		);
	}

	private String getRandomName() {
		return pullOfNames.get((int) (Math.random() * pullOfNames.size()));
	}

	private String getRandomSurname() {
		return pullOfSurnames.get((int) (Math.random() * pullOfSurnames.size()));
	}

	private String getRandomFamily() {
		return pullOfFamilies.get((int) (Math.random() * pullOfFamilies.size()));
	}

	public static int getRandomBeforeFive() {
		return (int) (Math.random() * 5) + 1;
	}
	public static int getRandomGroup() {
		return (int) (Math.random() * 2) + 1;
	}

	private HashMap<SchoolSubjects, List<Integer>> getSchoolGrades() {

		HashMap<SchoolSubjects, List<Integer>> schoolGrades = new HashMap<>();
		for (SchoolSubjects subject : SchoolSubjects.values()) {
			schoolGrades.put(subject, getRandomMarksList());
		}
		return schoolGrades;
	}

	private List<Integer> getRandomMarksList() {
		List<Integer> marks = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			marks.add(getRandomBeforeFive());
		}
		return marks;
	}
}