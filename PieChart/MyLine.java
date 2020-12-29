package application;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyLine extends MyShape {
	// PRIVATE MEMBER VARIABLES
	private MyPoint start;
	private MyPoint end;

	// CONSTRUCTORS
	public MyLine(MyPoint end) {
		this(end.getX(), end.getY());
	}
	public MyLine(double x, double y) {
		this(x, y, MyColor.BLACK.getColor());
	}
	
	public MyLine(MyPoint end, Color color) {
		this(end.getX(), end.getY(), color);
	}
	
	public MyLine(double x, double y, Color color) {
		this(0, 0, x, y, color);
	}
	
	public MyLine(MyPoint start, MyPoint end) {
		this(start.getX(), start.getY(), end.getX(), end.getY());
	}
	
	public MyLine(double x1, double y1, double x2, double y2) {
		this(x1, y1, x2, y2, MyColor.BLACK.getColor());
	}
	
	public MyLine(MyPoint start, MyPoint end, Color color) {
		this(start.getX(), start.getY(), end.getX(), end.getY(), color);
	}
	
	public MyLine(double x1, double y1, double x2, double y2, Color color) {
		super(color);
		start = new MyPoint(x1, y1);
		end = new MyPoint(x2, y2);
	}

	// MEMBER FUNCTIONS
	public double getLength() {
		return start.distance(end);
	}

	public double get_xAngle() {
		double angle = Math.toDegrees(Math.atan2(end.getY() - start.getY(), end.getX() - start.getX()));
		if (angle < 0)
			angle += 360;
		return angle;
	}
	
	public MyPoint getMidPoint() {
		return new MyPoint((end.getX() + start.getX()) / 2, (end.getY() + start.getY()) / 2);
	}

	// OVERWRITE METHODS
	@Override
	public void draw(GraphicsContext GC) {
		GC.setStroke(getColor());
		GC.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		
		output.append(String.format("%-15s %s    %n", "Start Point:", start));
		output.append(String.format("%-15s %s    %n", "End Point:", end));
		output.append(String.format("%-15s %,.3f %n", "Length:", getLength()));
		output.append(String.format("%-15s %,.3f %n", "Angle:", get_xAngle()));
		
		return output.toString();
	}
	
	// INTERFACE METHODS
	@Override
	public MyRectangle getMyBoundingRectangle() {
		final MyPoint topLeft = new MyPoint(start.getX() < end.getX() ? start.getX() : end.getX(), start.getY() < end.getY() ? start.getY() : end.getY());
		return new MyRectangle(topLeft, Math.abs(end.getX() - start.getX()), Math.abs(end.getY() - start.getY()));
	}
	
	@Override
	public ArrayList<MyPoint> getMyArea() {
		ArrayList<MyPoint> area = new ArrayList<>();
		
		final double TOLERANCE = 1;
		
		final double slope = (end.getY() - start.getY()) / (end.getX() - start.getX());
		final double b = start.getY() - (slope * start.getX());
		
		final MyRectangle bound = getMyBoundingRectangle();
		for (int x = (int)bound.getX(); x <= bound.getX() + bound.getWidth(); ++x)
			for (int y = (int)bound.getY(); y <= bound.getY() + bound.getHeight(); ++y)
				if (Math.abs(y - (slope * x + b)) < TOLERANCE)
					area.add(new MyPoint((int)x, (int)y));
		
		if (Math.abs(get_xAngle() - 90) < TOLERANCE)
			for (int i = 0; i < getLength(); ++i)
				area.add(new MyPoint((int)start.getX(), (int)start.getY() + i));
		else if (Math.abs(get_xAngle() - 90 * 3) < TOLERANCE)
			for (int i = 0; i < getLength(); ++i)
				area.add(new MyPoint((int)start.getX(), (int)start.getY() - i));
	
		return area;
	}
}