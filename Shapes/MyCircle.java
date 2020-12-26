package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyCircle extends MyShape {
	// PRIVATE MEMBER VARIABLES
	private double radius;

	// CONSTRUCTORS
	public MyCircle(double x, double y, double radius, Color color) {
		super(x, y, color);
		this.radius = radius;
	}

	// MEMBER FUNCTIONS
	public double getArea() {
		return Math.PI * Math.pow(radius, 2);
	}

	public double getPerimeter() {
		return 2 * Math.PI * radius;
	}

	public double getRadius() {
		return radius;
	}

	// OVERWRITE METHODS
	@Override
	public void draw(GraphicsContext GC) {
		GC.setFill(getColor());
		GC.fillOval(getX() - radius, getY() - radius, 2 * radius, 2 * radius);
		
		//GC.strokeRect(getX() - radius, getY() - radius, 2 * radius, 2 * radius);
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		
		output.append(String.format("%-15s (%,.3f, %,.3f) %n", "Center Point:", getX(), getY()));
		output.append(String.format("%-15s %,.3f %n", "Radius:", radius));
		output.append(String.format("%-15s %,.3f %n", "Perimeter:", getPerimeter()));
		output.append(String.format("%-15s %,.3f %n", "Area:", getArea()));
		
		return output.toString();
	}
}