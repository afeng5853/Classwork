package Shapes;

public class Triangle implements Shape {
	private double side1;
	private double side2;
	private double side3;
	
	public Triangle(double side1, double side2, double side3) throws Exception {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
		if (Double.isNaN(heronsFormula(side1, side2, side3))) {
			System.out.println(side1);
			System.out.println(side2);
			System.out.println(side3);
			throw new Exception("Invalid Triangle");
		}
	}

	private double heronsFormula(double side1, double side2, double side3) {
		double s = calculatePerimeter() / 2;
		return Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
	}
	
	public double calculateArea() {
		return heronsFormula(side1, side2, side3);
	}

	public double calculatePerimeter() {
		return this.side1 + this.side2 + this.side3;
	}

	@Override
	public String toString() {
		// To be written by student
		return "Triangle Sides: " + side1 + " " + side2 + " " + side3 + " Area: " + this.calculateArea() + " Perimeter: " + this.calculatePerimeter();
	}
}
