package application;

import javafx.scene.canvas.GraphicsContext;

public class MyPoint {
	// PRIVATE MEMBER VARIABLES
	double x, y;
	
	// CONSTRUCTORS
	public MyPoint() {
		this(0, 0);
	}
	
	public MyPoint(MyPoint p) {
		this(p.getX(), p.getY());
	}
	
	public MyPoint(double x, double y) {
		setPoint(x, y);
	}
	
	// MEMBER FUNCTIONS
	public void translate(double x, double y) {
		setPoint(this.x + x, this.y + y);
	}
	
	public void translate(MyPoint other) {
		translate(other.getX(), other.getY());
	}
	
	public double distance(double x, double y) {
		return Math.hypot(getX() - x, getY() - y);
	}
	
	public double distance(MyPoint other) {
		return distance(other.getX(), other.getY());
	}
	
	@Override
	public String toString() {
		return String.format("(%,.3f, %,.3f)", x, y);
	}
	
	public void draw(GraphicsContext GC) {
		GC.fillOval(x, y, 1, 1);
	}
	
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
	
	public MyPoint getPoint() {
		return new MyPoint(x, y);
	}
	public void setPoint(double x, double y) {
		setX(x);
		setY(y);
	}
	public void setPoint(MyPoint p) {
		setPoint(p.getX(), p.getY());
	}
	
	// SETS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyPoint other = (MyPoint) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
}
