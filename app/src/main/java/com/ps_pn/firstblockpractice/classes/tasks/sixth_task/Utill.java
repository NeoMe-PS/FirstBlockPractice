package com.ps_pn.firstblockpractice.classes.tasks.sixth_task;

import com.ps_pn.firstblockpractice.classes.tasks.sixth_task.entity.Enrollee;

import java.util.ArrayList;
import java.util.List;

public class Utill {

	public static List<Enrollee> getEnrolleList() {
		List<Enrollee> enrollees = new ArrayList<>();
		enrollees.add(new Enrollee("Castiel"));
		enrollees.add(new Enrollee("Din"));
		enrollees.add(new Enrollee("Crowly"));
		enrollees.add(new Enrollee("Sam"));
		enrollees.add(new Enrollee("Bobbi"));
		return enrollees;
	}


}
