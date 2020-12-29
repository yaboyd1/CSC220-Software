package application;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyOval extends MyShape {
	// PRIVATE MEMBER VARIABLES
	MyRectangle oval;
	
	// CONSTRUCTORS
	public MyOval(MyOval other) {
		this(other.getX(), other.getY(), other.getWidth(), other.getHeight(), other.getColor());
	}
	
	public MyOval(MyPoint topLeft, double width, double height) {
		this(topLeft, width, height, MyColor.BLACK.getColor());
	}
	
	public MyOval(double x, double y, double width, double height) {
		this(x, y, width, height, MyColor.BLACK.getColor());
	}
	
	public MyOval(MyPoint topLeft, double width, double height, Color color) {
		this(topLeft.getX(), topLeft.getY(), width, height, color);
	}
	
	public MyOval(double x, double y, double width, double height, Color color) {
		super(x, y, color);
		oval = new MyRectangle(x, y, width, height, color);
	}
	
	// MEMBER FUNCTIONS
	public double getPerimeter() {
		return 2 * Math.PI * Math.sqrt((Math.pow(getMajorAxis(), 2) + Math.pow(getMinorAxis(), 2)) / 2);
	}
	
	public double getArea() {
		return Math.PI * getMajorAxis() * getMinorAxis();
	}
	
	public double getMajorAxis() {
		return Math.max(getWidth() / 2, getHeight() / 2);
	}
	
	public double getMinorAxis() {
		return Math.min(getWidth() / 2, getHeight() / 2);
	}
	
	public void setAxes(double horizontal, double vertical) {
		oval.setHeight(horizontal / 2);
		oval.setWidth(vertical / 2);
	}
	
	public MyPoint getCenter() {
		return new MyPoint(getX() + getWidth() / 2, getY() + getHeight() / 2);
	}
	
	public void setCenter(MyPoint center) {
		setCenter(center.getX(), center.getY());
	}
	
	public void setCenter(double x, double y) {
		MyPoint topLeft = new MyPoint(x - oval.getWidth() / 2, y - oval.getHeight() / 2);
		setPoint(topLeft);
		oval.setPoint(topLeft);
	}
	
	public double getRadius(double angle) {
		angle = Math.toRadians(-angle);
		double a = getMajorAxis();
		double b = getMinorAxis();
		return (a * b) / Math.hypot(a * Math.sin(angle), b * Math.cos(angle));
	}
	
	// OVERWRITE METHODS
	public void draw(GraphicsContext GC) {
		GC.setFill(getColor());
		GC.fillOval(getX(), getY(), getWidth(), getHeight());
	}
	
	public String toString() {
		StringBuilder output = new StringBuilder();
		
		output.append(String.format("%-15s %s    %n", "Center Point:", getCenter()));
		output.append(String.format("%-15s %,.3f %n", "Major Axis:", getMajorAxis()));
		output.append(String.format("%-15s %,.3f %n", "Minor Axis:", getMinorAxis()));
		output.append(String.format("%-15s %,.3f %n", "Perimeter:", getPerimeter()));
		output.append(String.format("%-15s %,.3f %n", "Area:", getArea()));
		
		return output.toString();
	}
	
	// GETTERS AND SETTERS
	public double getWidth() {
		return oval.getWidth();
	}
	public void setWidth(double width) {
		oval.setWidth(width);
	}
	
	public double getHeight() {
		return oval.getHeight();
	}
	public void setHeight(double height) {
		oval.setHeight(height);
	}
	
	// INTERFACE METHODS
	@Override
	public MyRectangle getMyBoundingRectangle() {
		return new MyRectangle(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public ArrayList<MyPoint> getMyArea() {
		final double a = getWidth() / 2;
		final double b = getHeight() / 2;
		final MyPoint center = getCenter();
		final double shiftx = center.getX();
		final double shifty = center.getY();
		
		ArrayList<MyPoint> area = new ArrayList<>();
		
		for (double x = getX(); x <= getX() + getWidth(); ++x)
			for (double y = getY(); y <= getY() + getHeight(); ++y)
				if (Math.pow((x - shiftx) / a, 2) + Math.pow((y - shifty) / b, 2) <= 1)
					area.add(new MyPoint((int)x, (int)y));
		
		return area;
	}
}
