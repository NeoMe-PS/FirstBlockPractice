package com.ps_pn.firstblockpractice.training.practise_task.third_task;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		Shape circle = new Circle(10);
		Shape rectangle = new Rectangle(4, 2);
		Shape square = new Square(5);
		List<Shape> shapes = new ArrayList<>();

		shapes.add(circle);
		shapes.add(rectangle);
		shapes.add(square);

		for (Shape shape:shapes){
			shape.area();
			shape.perimeter();
		}
	}

}
