package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class MyShape implements MyShapeInterface {
	// PRIVATE MEMBER VARIABLES
	private MyPoint point;
	private Color color;

	// CONSTRUCTORS
	public MyShape() {
		this(MyColor.BLACK.getColor());
	}
	
	public MyShape(Color color) {
		this(0, 0, color);
	}
	
	public MyShape(MyPoint p) {
		this(p.getX(), p.getY());
	}

	public MyShape(double x, double y) {
		this(x, y, MyColor.BLACK.getColor());
	}
	
	public MyShape(MyPoint p, Color color) {
		this(p.getX(), p.getY(), color);
	}
	
	public MyShape(double x, double y, Color color) {
		point = new MyPoint(x, y);
		this.color = color;
	}

	// OVERRIDE METHODS
	public abstract void draw(GraphicsContext GC);
	public abstract String toString();

	// GETTERS and SETTERS
	public double getX() {
		return point.getX();
	}
	public void setX(double x) {
		point.setX(x);
	}

	public double getY() {
		return point.getY();
	}
	public void setY(double y) {
		point.setY(y);
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	// MY POINT METHODS INSIDE MYSHAPE
	public void translate(double x, double y) {
		point.translate(x, y);
	}
	
	public void translate(MyPoint other) {
		point.translate(other);
	}
	
	public double distance(double x, double y) {
		return point.distance(x, y);
	}
	
	public double distance(MyPoint other) {
		return point.distance(other.getX(), other.getY());
	}
	
	public MyPoint getPoint() {
		return point;
	}
	public void setPoint(double x, double y) {
		point.setPoint(x, y);
	}
	public void setPoint(MyPoint p) {
		point.setPoint(p.getX(), p.getY());
	}
}