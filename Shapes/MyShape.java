package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class MyShape {
	// PRIVATE MEMBER VARIABLES
	private double x, y;
	private Color color;

	// CONSTRUCTORS
	public MyShape() {
		this(Color.BLACK);
	}
	
	public MyShape(Color color) {
		this(0, 0, color);
	}

	public MyShape(double x, double y) {
		this(x, y, Color.BLACK);
	}
	
	public MyShape(double x, double y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	// OVERRIDE METHODS
	public abstract void draw(GraphicsContext GC);
	public abstract String toString();

	// GETTERS and SETTERS
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
}