package application;

import java.util.Random;

import javafx.scene.paint.Color;

public enum MyColor {
	
	BLACK(0, 0, 0),
	WHITE(255, 255, 255),
	
	GREY(200, 200, 200),
	
	RED(255, 0, 0),
	ORANGE(255, 165, 0),
	YELLOW(255, 255, 0),
	GREEN(0, 255, 0),
	BLUE(0, 0, 255),
	PURPLE(128, 0, 128);
	
	private int r, g, b;
	private int a;
	
	MyColor(int r, int g, int b) {
		this(r, g, b, 255);
	}
	
	MyColor(int r, int g, int b, int a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
		
	public Color getColor() {
		return Color.rgb(r, g, b, (double)a / 255);
	}
	
	public static Color randomColor() {
		Random rand = new Random();
		return Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
	}
	
	public static Color random() {
		Random rand = new Random();
		return Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), rand.nextDouble());
	}
}
