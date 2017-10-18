package Shapes;

public class Square extends Rectangle {

	double side;

	public Square(int side) {
		super(side, side);
		this.side = side;
	}

	@Override
	public String toString() {
		// To be written by student
		return "Square Side: " + side + " Area: " + this.calculateArea() + " Perimeter: " + this.calculatePerimeter();
	}

}
