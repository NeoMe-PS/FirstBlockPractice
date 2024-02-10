package com.ps_pn.firstblockpractice.classes.tasks.sixth_task.entity;

public class Exam {
	private String name;

	private int ball;



	public Exam() {
	}


	public int getBall() {
		return ball;
	}

	public void setBall(int ball) {
		this.ball = ball;
	}

	@Override
	public String toString() {
		return "Exam{" +
				"name='" + name + '\'' +
				", ball=" + ball +
				'}';
	}
}
