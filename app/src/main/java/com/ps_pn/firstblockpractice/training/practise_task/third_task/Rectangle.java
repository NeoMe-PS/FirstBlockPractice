package com.ps_pn.firstblockpractice.training.practise_task.third_task;

public class Rectangle implements Shape{

	private int length;
	private int width;

	public Rectangle(int length, int width) {
		this.length = length;
		this.width = width;
	}

	@Override
	public void perimeter() {
		System.out.println("Rectangle perimeter: " + 2 * (length + width));
	}

	@Override
	public void area() {
		System.out.println("Rectangle area: " + width * length);
	}
}
