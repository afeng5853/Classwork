package Shapes;

public class Trapezoid extends Rectangle {
	private double height;
	
	public Trapezoid(double length, double width, double height) {
		super(length, width);
		this.height = height;
	}
	
	public double getArea() {
		return ((length + width) / 2.0) * height;
	}
	
	public double getPerimeter() {
		double c = pythagoream(Math.abs(length - width), height);
		return length + width + c;
	}
	
	private double pythagoream(double a, double b) {	
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2))
	}
}
