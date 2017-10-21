package Shapes;
/**
 * The Rhombus class
 * @author alex
 *
 */
public class Rhombus extends Rectangle {
	private double diagonal;
	private double side;
	
	/**
	 * constructs a Rhombus
	 * @param length
	 * @param diagonal
	 */
	public Rhombus(double length, double diagonal) {
		super(length, length);
		this.diagonal = diagonal;
		this.side = length;
	}
	
	/**
	 * calculates the area of the rhombus
	 * @return the area of the rhombus
	 */
	@Override
	public double calculateArea() {
		return 0.5 * diagonal * Math.sqrt(4*(side*side) - (diagonal)*(diagonal));
	}
	
	// perimeter function is same as rectangle
	
	/**
	 * the toString method for a rhombus
	 * @return a string representing the rhombus
	 */
	@Override
	public String toString() {
		return "Rhombus Side: " + side  + " Diagonal: " + diagonal +  " Area: " + this.calculateArea() + " Perimeter: " + this.calculatePerimeter();
	}
}
