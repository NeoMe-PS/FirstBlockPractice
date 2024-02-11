package com.ps_pn.firstblockpractice.classes.tasks.sixth_task.entity;

public enum Mark {

	GREAT(5),
	GOOD (4),
	SATISFACTORY(3),
	UNSATISFACTORY(2);

	private final int ball;

	Mark(int ball) {
		this.ball = ball;
	}

	public int getBall() {
		return ball;
	}

	@Override
	public String toString() {
		return "Mark{" +
				"ball=" + ball +
				'}';
	}
}
