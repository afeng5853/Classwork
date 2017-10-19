package Shapes;

import java.util.Random;

/**
 * @author Mr Levin Created 10/16/2017 Lab 2.1 shapes
 *
 */

public class ShapeUtilities {

	/**
	 * Creates a random shape from the choices.
	 * 
	 * @return Shape Student Work: Update with your own shape.
	 * @throws Exception 
	 */
	public static Shape randomShape() throws Exception {
		Random rand = new Random();
		int x = rand.nextInt(4);

		switch (x) {
		case 0:
			return new Circle(rand.nextInt(100));
		case 1:
			return new Rectangle(rand.nextInt(50), rand.nextInt(50));
		case 2:
			return new Square(rand.nextInt(50));
		case 3:
        	return new Triangle(rand.nextInt(10)+20, rand.nextInt(5)+10, rand.nextInt(5)+20);
		default:
			return new Circle(rand.nextInt(100));
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
