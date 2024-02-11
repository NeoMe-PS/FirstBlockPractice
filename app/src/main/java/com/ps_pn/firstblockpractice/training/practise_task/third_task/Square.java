package com.ps_pn.firstblockpractice.training.practise_task.third_task;

public class Square implements Shape {

	private int sideLength;

	public Square(int sideLength) {
		this.sideLength = sideLength;
	}

	@Override
	public void perimeter() {
		System.out.println("Square perimeter: " + 4 * sideLength);
	}

	@Override
	public void area() {
		System.out.println("Square area: " + sideLength * sideLength);
	}
}
