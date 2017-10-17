package Shapes;

public class Circle implements Shape {
	private double radius;
	private static double PI = 3.14;
	
	public Circle(double radius) {
		this.radius = radius;
	}
	@Override
	public double area() {
		
		return radius * radius * PI;
	}

	@Override
	public double perimeter() {
		
		return 2 * radius * PI;
	}
	
}
