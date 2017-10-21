package Shapes;

/**
 * the Triangle class
 * @author alex
 *
 */
public class Triangle implements Shape {
	private double side1;
	private double side2;
	private double side3;
	
	/**
	 * constructs a triangle
	 * @param side1
	 * @param side2
	 * @param side3
	 * @throws Exception if triangle cannot be constructed due to invalid side lengths
	 */
	public Triangle(double side1, double side2, double side3) throws Exception {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
		if (Double.isNaN(heronsFormula(side1, side2, side3))) {
			// If heron's formula returns a complex number, then it isn't a valid triangle
			throw new Exception("Invalid Triangle");
		}
	}

	/**
	 * Heron's formula to calculate area
	 * @param side1 first side
	 * @param side2 second side
	 * @param side3 third side
	 * @return the area of the triangle
	 */
	private double heronsFormula(double side1, double side2, double side3) {
		double s = calculatePerimeter() / 2;
		return Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
	}
	
	/**
	 * returns the area of the triangle
	 * @see heronsFormula
	 * @return the area of the triangle
	 */
	public double calculateArea() {
		return heronsFormula(side1, side2, side3);
	}

	/**
	 * calculates the perimeter of the triangle
	 * @return the perimeter of the triangle
	 */
	public double calculatePerimeter() {
		return this.side1 + this.side2 + this.side3;
	}

	/**
	 * the toString method for a triangle
	 * @return a string representing the triangle
	 */
	@Override
	public String toString() {
		return "Triangle Sides: " + side1 + " " + side2 + " " + side3 + " Area: " + this.calculateArea() + " Perimeter: " + this.calculatePerimeter();
	}
}
