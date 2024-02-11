package com.ps_pn.firstblockpractice.classes.tasks.sixth_task.entity;

import java.util.ArrayList;
import java.util.List;

public class Teacher {

	public List<Mark> checkEnrollee(Enrollee enrollee) {
		List<Mark> listOfMarks = new ArrayList<>();
		List<Exam> exams = enrollee.getFaculty().getExams();
		for (Exam exam : exams) {
			listOfMarks.add(getMark(exam.getBall()));
		}
		return listOfMarks;
	}

	private Mark getMark(int rightAnswers) {
		if (rightAnswers < 5) {
			return Mark.UNSATISFACTORY;
		} else if (rightAnswers < 6) {
			return Mark.SATISFACTORY;
		} else if (rightAnswers < 8) {
			return Mark.GOOD;
		} else return Mark.GREAT;
	}
}