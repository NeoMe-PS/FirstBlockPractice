package com.ps_pn.firstblockpractice.classes.tasks.third_task;

public class Main {



	public static void main(String[] args) {
		Point a = new Point(4, 2);
		Point b = new Point(5, 5);
		Point c = new Point(6, 6);

		Triangle triangle = new Triangle(a, b, c);

		System.out.println(triangle.getPerimeter());
		System.out.println(triangle.getArea());
		System.out.println(triangle.getMedianCross());

	}
}
