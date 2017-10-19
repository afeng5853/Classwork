package Shapes;

/**
 * @author Mr Levin Created 10/16/2017 Lab 2.1 shapes
 *
 */
public class Rectangle implements Shape {

	protected double length;
	protected double width;

	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}


	public double calculateArea() {
		// To be written by student
		return this.length * this.width;
	}


	public double calculatePerimeter() {
		// To be written by student
		return 2 * this.length + 2 * this.width;
	}


	public String toString() {
		return "Rectangle Width: " + width + " Length: " + length + " Area: " + this.calculateArea() + " Perimeter: "
				+ this.calculatePerimeter();
	}
}
