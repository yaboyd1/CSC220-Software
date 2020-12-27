package application;

import java.util.ArrayList;
import java.util.HashSet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyPolygon extends MyShape {
	// PRIVATE MEMBER VARIABLES
	private int sides;
	private double radius;

	// CONSTRUCTORS
	public MyPolygon(MyPoint center, double radius) {
		this(center, 6, radius);
	}
	
	public MyPolygon(MyPoint center, int sides, double radius) {
		this(center, sides, radius, MyColor.BLACK.getColor());
	}
	
	public MyPolygon(double x, double y, double radius) {
		this(x, y, 6, radius);
	}
	
	public MyPolygon(double x, double y, int sides, double radius) {
		this(x, y, sides, radius, MyColor.BLACK.getColor());
	}
	
	public MyPolygon(MyPoint center, double radius, Color color) {
		this(center.getX(), center.getY(), radius, color);
	}
	
	public MyPolygon(double x, double y, double radius, Color color) {
		this(x, y, 6, radius, color);
	}
	
	public MyPolygon(MyPoint center, int sides, double radius, Color color) {
		this(center.getX(), center.getY(), sides, radius, color);
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
	
	public ArrayList<MyPoint> getVertices() {
		return getVertices(radius);
	}
	
	public ArrayList<MyPoint> getVertices(double r) {
		ArrayList<MyPoint> vertices = new ArrayList<>(sides);
		
		final double angle = (2 * Math.PI) / sides;
		for (int i = 0; i < sides; ++i)
			vertices.add(new MyPoint(getX() + r * -Math.sin((i + 1) * angle), getY() + r * -Math.cos((i + 1) * angle)));
		
		return vertices;
	}

	// OVERWRITE METHODS
	@Override
	public void draw(GraphicsContext GC) {
		double xValues[] = new double[sides];
		double yValues[] = new double[sides];
		
		ArrayList<MyPoint> vertices = getVertices();
		for (int i = 0; i < sides; ++i) {
			xValues[i] = vertices.get(i).getX();
			yValues[i] = vertices.get(i).getY();
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

	// INTERFACE METHODS
	@Override
	public MyRectangle getMyBoundingRectangle() {
		return new MyRectangle(getX() - radius, getY() - radius, radius * 2, radius * 2);
	}

	@Override
	public ArrayList<MyPoint> getMyArea() {
		ArrayList<MyLine> lines = new ArrayList<>((int)(sides * getRadius()));
		
		// Find all of the lines inside the polygon, from vertex to vertex
		ArrayList<MyPoint> vertices;
		for (double r = 0; r < getRadius(); ++r) {
			vertices = getVertices(r);
			for (int i = 0; i < sides; ++i)
				lines.add(new MyLine(vertices.get(i), vertices.get((i + 1) % sides), getColor()));
			vertices.clear();
		}
		
		// Get area of each line, no duplicates
		HashSet<MyPoint> noDupes = new HashSet<>();
		for (int i = 0; i < lines.size(); ++i)
			noDupes.addAll(lines.get(i).getMyArea());
		
		return new ArrayList<>(noDupes);
	}
}