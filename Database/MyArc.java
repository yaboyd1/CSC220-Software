package application;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class MyArc extends MyShape {
	// PRIVATE MEMBER VARIABLES
	private MyOval oval;
	private double startAngle;
	private double angle;
	
	// CONSTRUCTORS
	public MyArc(MyOval other, double startAngle, double angle, Color color) {
		this(other.getX(), other.getY(), other.getWidth(), other.getHeight(), startAngle, angle, color);
	}
	
	public MyArc(double x, double y, double width, double height, double startAngle, double angle, Color color) {
		super(x, y, color);
		oval = new MyOval(x, y, width, height, color);
		this.startAngle = startAngle;
		this.angle = angle;
	}
	
	// GETTERS AND SETTERS
	public double getStart() {
		return startAngle;
	}
	public void setStart(double startAngle) {
		this.startAngle = startAngle;
	}
	
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public MyOval getOval() {
		return this.oval;
	}
	public void setOval(MyOval other) {
		oval = new MyOval(other);
	}
	
	// OVERWRITE METHODS
	@Override
	public void draw(GraphicsContext GC) {
		GC.setFill(getColor());
		GC.fillArc(oval.getX(), oval.getY(), oval.getWidth(), oval.getHeight(), startAngle, angle, ArcType.ROUND);
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		
		output.append(String.format("%-15s %s    %n", "Center Point:", oval.getCenter()));
		output.append(String.format("%-15s %,.3f %n", "Start Angle:", startAngle));
		output.append(String.format("%-15s %,.3f %n", "Angle Length:", angle));
		output.append(oval.toString());
		
		return output.toString();
	}
	
	// INTERFACE METHODS
	@Override
	public MyRectangle getMyBoundingRectangle() {
		return oval.getMyBoundingRectangle();
	}

	@Override
	public ArrayList<MyPoint> getMyArea() {
		return oval.getMyArea();
	}
}
