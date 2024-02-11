package com.ps_pn.firstblockpractice.classes.tasks.sixth_task.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntranceExamsSystem {

	private List<Enrollee> enteredTheFaculty = new ArrayList<>();

	public List<Enrollee> getEnteredTheFaculty() {
		return enteredTheFaculty;
	}

	public void makeDecisionOnAdmission(List<Enrollee> enrollees, Teacher teacher) {

		for (Enrollee enrollee : enrollees) {
			List<Mark> marks = teacher.checkEnrollee(enrollee);
			double avg = marks.stream().map(Mark::getBall).mapToInt(Integer::intValue).average().getAsDouble();
			enrollee.setAvg(avg);
			decide(enrollee);
		}
	}

	private void decide(Enrollee enrollee) {
		if (!checkAvg(enrollee)) {
			enrollee.setEnrolled(false);
			return;
		}
		enrollee.setEnrolled(true);
		enteredTheFaculty.add(enrollee);
		enrollee.getFaculty().getEnrollees().add(enrollee);
	}

	private boolean checkAvg(Enrollee enrollee) {
		double minAvgInFaculty = enrollee.getFaculty().getMinAvg();
		return enrollee.getAvg() >= minAvgInFaculty;
	}

	public void registerEnrolleeInFaculty(List<Enrollee> enrollees, Faculty... faculties) throws Exception {
		if (faculties.length == 0) {
			throw new Exception("Faculties must be more than 0 .Your value is: " + faculties.length);
		}
		for (Enrollee enrollee : enrollees) {
			int randomFaculty = new Random().nextInt(faculties.length);
			enrollee.registerOnFaculty(faculties[randomFaculty]);
		}
	}

	public void enrollessPassExams(List<Enrollee> enrollees) {
		for (Enrollee enrollee : enrollees) {
			enrollee.passExams();
		}
	}

	private void teacherCheckEnrollee(List<Enrollee> enrollees, Teacher teacher) {
		for (Enrollee enrollee : enrollees) {
			teacher.checkEnrollee(enrollee);
		}
	}
}