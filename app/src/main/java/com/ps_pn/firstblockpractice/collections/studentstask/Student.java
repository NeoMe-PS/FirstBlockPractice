package com.ps_pn.firstblockpractice.collections.studentstask;

import java.util.HashMap;
import java.util.List;

public class Student {

	private String family;
	private String name;
	private String surname;
	private int yearOfBirthday;
	private int course;
	private int numOfGroup;

	private HashMap<SchoolSubjects, List<Integer>> schoolGrades;

	public Student(String family, String name, String surname, int yearOfBirthday,
	               int course, int numOfGroup, HashMap<SchoolSubjects,
			List<Integer>> schoolGrades) {

		this.family = family;
		this.name = name;
		this.surname = surname;
		this.yearOfBirthday = yearOfBirthday;
		this.course = course;
		this.numOfGroup = numOfGroup;
		this.schoolGrades = schoolGrades;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getYearOfBirthday() {
		return yearOfBirthday;
	}

	public void setYearOfBirthday(int yearOfBirthday) {
		this.yearOfBirthday = yearOfBirthday;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public int getNumOfGroup() {
		return numOfGroup;
	}

	public void setNumOfGroup(int numOfGroup) {
		this.numOfGroup = numOfGroup;
	}

	public HashMap<SchoolSubjects, List<Integer>> getSchoolGrades() {
		return schoolGrades;
	}

	public void setSchoolGrades(HashMap<SchoolSubjects, List<Integer>> schoolGrades) {
		this.schoolGrades = schoolGrades;
	}

	@Override
	public String toString() {
		return "\n----------------\nStudent: " + family + ", " + name + ", " + surname +
				"\nYear of birthday: " + yearOfBirthday +
				"\nCourse: " + course +
				"\nGroup: " + numOfGroup +
				"\nSchool grades: " + schoolGrades;
	}


}



