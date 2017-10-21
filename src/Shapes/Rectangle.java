package Shapes;

/**
 * @author Mr Levin Created 10/16/2017 Lab 2.1 shapes
 * @author Alex Feng Edited 10/21/2017
 *
 */
public class Rectangle implements Shape {

	private double length;
	private double width;

	/**
	 * constructs a Rectangle
	 * @param length
	 * @param width
	 */
	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	/**
	 * calculates the area of a rectangle
	 * @return the area of the rectangle
	 */
	public double calculateArea() {
		// To be written by student
		return this.length * this.width;
	}

	/**
	 * calculates the perimeter of a rectangle
	 * @return the perimeter of the rectangle
	 */
	public double calculatePerimeter() {
		// To be written by student
		return 2 * this.length + 2 * this.width;
	}

	/**
	 * the toString method for a rectangle
	 * @return a string representing the rectangle
	 */
	public String toString() {
		return "Rectangle Width: " + width + " Length: " + length + " Area: " + this.calculateArea() + " Perimeter: "
				+ this.calculatePerimeter();
	}
}
