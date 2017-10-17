package Shapes;

public class Rectangle implements Shape {
	private double length;
	private double width;
	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}
	@Override
	public double area() {
		
		return length * width;
	}

	@Override
	public double perimeter() {
		
		return 2*length + 2*width;
	}
}
