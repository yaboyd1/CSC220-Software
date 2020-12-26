package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyLine extends MyShape {
	// PRIVATE MEMBER VARIABLES
	private double x1, y1;
	private double x2, y2;

	// CONSTRUCTORS
	public MyLine(double x, double y) {
		this(x, y, Color.BLACK);
	}
	
	public MyLine(double x, double y, Color color) {
		super(0, 0, color);
		this.x2 = x;
		this.y2 = y;
	}
	
	public MyLine(double x1, double y1, double x2, double y2) {
		this(x1, y1, x2, y2, Color.BLACK);
	}
	
	public MyLine(double x1, double y1, double x2, double y2, Color color) {
		super(color);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	// MEMBER FUNCTIONS
	public double getLength() {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

	public double get_xAngle() {
		return Math.toDegrees(Math.atan((y2 - y1) / (x2 - x1)));
	}

	// OVERWRITE METHODS
	@Override
	public void draw(GraphicsContext GC) {
		GC.setStroke(getColor());
		GC.strokeLine(x1, y1, x2, y2);
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		
		output.append(String.format("%-15s (%,.3f, %,.3f) %n", "Start Point:", x1, y1));
		output.append(String.format("%-15s (%,.3f, %,.3f) %n", "End Point:", x2, y2));
		output.append(String.format("%-15s %,.3f %n", "Length:", getLength()));
		output.append(String.format("%-15s %,.3f %n", "Angle:", get_xAngle()));
		
		return output.toString();
	}
}