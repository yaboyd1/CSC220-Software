package application;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyCircle extends MyOval {
	// PRIVATE MEMBER VARIABLES
	private double radius;

	// CONSTRUCTORS
	public MyCircle(MyPoint center, double radius) {
		this(center, radius, MyColor.BLACK.getColor());
	}
	
	public MyCircle(double x, double y, double radius) {
		this(x, y, radius, MyColor.BLACK.getColor());
	}
	
	public MyCircle(MyPoint center, double radius, Color color) {
		this(center.getX(), center.getY(), radius, color);
	}
	
	public MyCircle(double x, double y, double radius, Color color) {
		super(x + radius, y + radius, radius * 2, radius * 2, color);
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
		
		output.append(String.format("%-15s %s    %n", "Center Point:", getPoint()));
		output.append(String.format("%-15s %,.3f %n", "Radius:", radius));
		output.append(String.format("%-15s %,.3f %n", "Perimeter:", getPerimeter()));
		output.append(String.format("%-15s %,.3f %n", "Area:", getArea()));
		
		return output.toString();
	}
	
	@Override
	public MyRectangle getMyBoundingRectangle() {
		return new MyRectangle(getX() - radius, getY() - radius, radius * 2, radius * 2);
	}
	
	@Override
	public ArrayList<MyPoint> getMyArea() {
		ArrayList<MyPoint> area = new ArrayList<>();
		
		for (double x = getX() - radius; x <= getX() + radius * 2; ++x)
			for (double y = getY() - radius; y <= getY() + radius * 2; ++y)
				if (Math.pow((x - getX()), 2) + Math.pow((y - getY()), 2) <= Math.pow(radius, 2))
					area.add(new MyPoint((int)x, (int)y));
		
		return area;
	}
}