package application;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyRectangle extends MyShape {
	// PRIVATE MEMBER VARIABLES
	private double width;
	private double height;
	
	// CONSTRUCTORS
	public MyRectangle(MyPoint topLeft, double width, double height) {
		this(topLeft.getX(), topLeft.getY(), width, height);
	}
	
	public MyRectangle(double x, double y, double width, double height) {
		this(x, y, width, height, MyColor.BLACK.getColor());
	}
	
	public MyRectangle(MyPoint topLeft, double width, double height, Color color) {
		this(topLeft.getX(), topLeft.getY(), width, height, color);
	}
	
	public MyRectangle(double x, double y, double width, double height, Color color) {
		super(x, y, color);
		this.width = width;
		this.height = height;
	}
	
	// MEMBER FUNCTIONS
	public double getPerimeter() {
		return 2 * width + 2 * height;
	}
	
	public double getArea() {
		return width * height;
	}
	
	// OVERWRITE METHODS
	@Override
	public void draw(GraphicsContext GC) {
		GC.setFill(getColor());
		GC.fillRect(getX(), getY(), width, height);
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		
		output.append(String.format("%-15s %s    %n", "Top Left Point:", getPoint()));
		output.append(String.format("%-15s %,.3f %n", "Width:", width));
		output.append(String.format("%-15s %,.3f %n", "Height:", height));
		output.append(String.format("%-15s %,.3f %n", "Perimeter: ", getPerimeter()));
		output.append(String.format("%-15s %,.3f %n", "Area:", getArea()));
		
		return output.toString();
	}
	
	// GETTERS AND SETTERS
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	
	// INTERFACE METHODS
	@Override
	public MyRectangle getMyBoundingRectangle() {
		return new MyRectangle(getX(), getY(), width, height);
	}

	@Override
	public ArrayList<MyPoint> getMyArea() {
		ArrayList<MyPoint> area = new ArrayList<>();
		for (double x = getX(); x <= getX() + width; ++x)
			for (double y = getY(); y <= getY() + height; ++y)
				area.add(new MyPoint((int)x, (int)y));
		return area;
	}
}
