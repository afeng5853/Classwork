package Shapes;

import java.util.Random;

public class utils {
	public static double sumArea(Shape[] shapeArr) {
		double sum = 0;
		for (Shape s: shapeArr) {
			sum += s.area();
		}
		return sum;
	}
	
	public static double sumPerimeter(Shape[] shapeArr) {
		double sum = 0;
		for (Shape s: shapeArr) {
			sum += s.perimeter();
		}
		return sum;
	}
	
	public static Shape getRandomShape() {
		Random r = new Random();
		int x = r.nextInt(3);
		switch (x) {
			case 0:
				return new Circle(r.nextInt(100)+1);
			case 1:
				return new Rectangle(r.nextInt(100)+1,r.nextInt(100)+1);
			default:
				return new Square(r.nextInt(100)+1);
		}
	}
}
