package com.ps_pn.firstblockpractice.classes.tasks.third_task;
/*
      III

      Описать класс, представляющий треугольник. Предусмотреть методы для создания объектов,
      вычисления площади, периметра и точки пересечения медиан.
      Описать свойства для получения состояния объекта.
     */

public class Triangle {
	private Point a;
	private Point b;
	private Point c;

	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public double getPerimeter() {
		double ab = Point.getLength(a, b);
		double bc = Point.getLength(b, c);
		double ac = Point.getLength(a, c);
		return ab + bc + ac;
	}

	public double getArea() {
		double ab = Point.getLength(a, b);
		double bc = Point.getLength(b, c);
		double ac = Point.getLength(a, c);
		double p = (ab + bc + ac) / 2;
		return Math.sqrt(p * (p - ab) * (p - bc) * (p - ac));
	}

	public Point getMedianCross() {
		return new Point((a.x + b.x + c.x) / 3, (a.y + b.y + c.y) / 3);
	}

	@Override
	public String toString() {
		return "Triangle{" +
				"a=" + a +
				", b=" + b +
				", c=" + c +
				'}';
	}
}