package com.ps_pn.firstblockpractice.collections.studentstask.comporators;

import com.ps_pn.firstblockpractice.collections.studentstask.Student;

import java.util.Comparator;

public class StudentCourseComporator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {

		return Integer.compare(o1.getCourse(), o2.getCourse());
	}
}