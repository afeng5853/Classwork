package Shapes;

public class ShapeRunner {
	public static void main(String[] args) {
		Rectangle rect = new Rectangle(3,5);
		Circle circle = new Circle(3);
		double rectArea = rect.area();
		double circleArea = circle.area();
		double rectPerimeter = rect.perimeter();
		double circlePerimeter = circle.perimeter();
		System.out.println("Biggest perimeter is " + 
							(rectPerimeter > circlePerimeter ? rectPerimeter : circlePerimeter));
		System.out.println("Biggest area is " + 
				(rectArea > circleArea ? rectArea : circleArea));
		Shape[] shapeArr = new Shape[] {rect, circle};
		System.out.println(utils.sumArea(shapeArr));
		System.out.println(utils.sumPerimeter(shapeArr));
		System.out.println(utils.getRandomShape());
	}
}
