package com.ps_pn.firstblockpractice.classes.tasks.sixth_task.entity;

import java.util.List;
import java.util.Random;

public class Enrollee {
	private String name;
	private double avg;
	private boolean enrolled;
	private Faculty faculty;


	public void passExams() {
		List<Exam> exams = faculty.getExams();
		for (Exam exam:exams){
			exam.setBall(new Random().nextInt(10));
		}
	}

	public Enrollee(String name) {
		this.name = name;
	}

	public void registerOnFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public String getName() {
		return name;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public boolean isEnrolled() {
		return enrolled;
	}

	public void setEnrolled(boolean enrolled) {
		this.enrolled = enrolled;
	}

	@Override
	public String toString() {
		return "Enrollee{" +
				"name='" + name + '\'' +
				", avg=" + avg +
				", enrolled=" + enrolled +
				", faculty=" + faculty.getName() +
				'}';
	}
}
