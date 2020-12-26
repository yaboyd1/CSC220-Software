package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyPolygon extends MyShape {
	// PRIVATE MEMBER VARIABLES
	private int sides;
	private double radius;

	// CONSTRUCTORS	
	public MyPolygon(double x, double y, double radius, Color color) {
		this(x, y, 6, radius, color);
	}
	
	public MyPolygon(double x, double y, int sides, double radius, Color color) {
		super(x, y, color);
		this.sides = sides;
		this.radius = radius;
	}

	// MEMBER FUNCTIONS
	public double getArea() {
		return (Math.pow(getSide(), 2) * sides) / (4 * Math.tan(Math.toRadians(180 / sides)));
	}

	public double getPerimeter() {
		return sides * getSide();
	}

	public double getAngle() {
		return ((sides - 2) * 180) / sides;
	}

	public double getSide() {
		return 2 * radius * Math.sin(Math.PI / sides);
	}
	
	public double getApothem() {
		return radius * Math.cos(Math.toRadians(180 / sides));
	}
	
	public double getRadius() {
		return radius;
	}

	// OVERWRITE METHODS
	@Override
	public void draw(GraphicsContext GC) {
		double xValues[] = new double[sides];
		double yValues[] = new double[sides];

		double angle = (2 * Math.PI) / sides;
		for (int i = 0; i < sides; ++i) {
			xValues[i] = getX() + radius * -Math.sin((i + 1) * angle);
			yValues[i] = getY() + radius * -Math.cos((i + 1) * angle);
		}
		
		GC.setFill(getColor());
		GC.fillPolygon(xValues, yValues, sides);
		
		//GC.strokeRect(getX() - radius, getY() - radius, 2 * radius, 2 * radius);
		//GC.strokeOval(getX() - radius, getY() - radius, 2 * radius, 2 * radius);
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		
		output.append(String.format("%-15s %d    %n", "Sides:", sides));
		output.append(String.format("%-15s %,.3f %n", "Side Length:", getSide()));
		output.append(String.format("%-15s %,.3f %n", "Interior Angle:", getAngle()));
		output.append(String.format("%-15s %,.3f %n", "Perimeter:", getPerimeter()));
		output.append(String.format("%-15s %,.3f %n", "Area:", getArea()));
		
		return output.toString();
	}
}