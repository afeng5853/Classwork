package Shapes;

import java.util.Random;

/**
 * @author Mr Levin Created 10/16/2017 Lab 2.1 shapes
 * @author Alex Feng Edited 10/21/2017
 */

public class ShapeUtilities {

	/**
	 * Creates a random shape from the choices.
	 * 
	 * @return Shape Student Work: Update with your own shape.
	 * @throws Exception if a shape cannot be constructed
	 */
	public static Shape randomShape() throws Exception {
		Random rand = new Random();
		int x = rand.nextInt(5);

		switch (x) {
		case 0:
			return new Circle(rand.nextInt(100) + 1);
		case 1:
			return new Rectangle(rand.nextInt(50) + 1, rand.nextInt(50) + 1);
		case 2:
			return new Square(rand.nextInt(50) + 1);
		case 3:
        	return new Triangle(rand.nextInt(10)+20, rand.nextInt(5)+10, rand.nextInt(5)+20);
		case 4:
			return new Rhombus(rand.nextInt(50) + 30, rand.nextInt(25) + 0.5);
		default:
			return new Circle(rand.nextInt(100) + 1);
		}

	}

	/**
	 * adds up the area of all the shapes in the array
	 * 
	 * @param shapes
	 * @return double
	 */
	public static double sumArea(Shape[] shapes) {
		double sum = 0;
		for (Shape s : shapes) {
			sum += s.calculateArea();
		}
		return sum;
	}

	/**
	 * adds up the perimeter of all the shapes in the array
	 * 
	 * @param shapes
	 * @return double
	 */
	public static double sumPerimeter(Shape[] shapes) {
		double sum = 0;
		for (Shape s : shapes) {
			sum += s.calculatePerimeter();
		}
		return sum;
	}

}
