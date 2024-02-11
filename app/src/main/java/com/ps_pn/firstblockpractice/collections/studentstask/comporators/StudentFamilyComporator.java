package com.ps_pn.firstblockpractice.collections.studentstask.comporators;

import com.ps_pn.firstblockpractice.collections.studentstask.Student;

import java.util.Comparator;

public class StudentFamilyComporator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {

		return o1.getFamily().compareTo(o2.getFamily());
	}
}