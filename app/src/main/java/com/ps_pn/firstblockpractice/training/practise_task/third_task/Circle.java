package com.ps_pn.firstblockpractice.training.practise_task.third_task;

public class Circle implements Shape {

	private int diameter;

	public Circle(int diameter) {
		this.diameter = diameter;
	}

	@Override
	public void perimeter() {
		System.out.println("Circle perimeter: " + Math.PI * diameter);
	}

	@Override
	public void area() {
		System.out.println("Circle area: " + (Math.PI / 4) * (diameter * diameter));
	}
}
