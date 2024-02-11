package com.ps_pn.firstblockpractice.classes.tasks.sixth_task.entity;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
	private String name;
	private List<Enrollee> enrollees = new ArrayList<>();
	private List<Exam> exams;
	private int type;
	private int minAvg;
	public static final int ZLO_TYPE = 0;
	public static final int DOBRO_TYPE = 1;

	{
		setExamsAtType();
	}

	private void setExamsAtType() {
		this.exams = new ArrayList<>();
		switch (type) {
			case DOBRO_TYPE:
				exams.add(new Exam());
				exams.add(new Exam());
				exams.add(new Exam());
				exams.add(new Exam());
				exams.add(new Exam());
				break;
			case ZLO_TYPE:
				exams.add(new Exam());
				break;
		}
	}

	public Faculty(String name, int type, int minAvg) {
		this.name = name;
		this.type = type;
		this.minAvg = minAvg;
	}

	public int getMinAvg() {
		return minAvg;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public String getName() {
		return name;
	}

	public List<Enrollee> getEnrollees() {
		return enrollees;
	}

	public void setEnrollees(List<Enrollee> enrollees) {
		this.enrollees = enrollees;
	}

	@Override
	public String toString() {
		return "Faculty{" +
				"name='" + name + '\'' +
				", enrollees=" + enrollees +
				", exams=" + exams +
				", minAvg=" + minAvg +
				'}';
	}
}
