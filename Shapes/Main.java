package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	// Canvas Size
	final static double WIDTH = 750;
	final static double HEIGHT = 500;
	
	// How much of the canvas a shape  should take up
	// Adjusts to smallest canvas dimension in drawInscribedHex()
	final static double RATIO = .85;
	
	// The minimum radius an inscribed shape can have
	final static double MIN = 10;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Canvas canvas = new Canvas(WIDTH, HEIGHT);
			
			drawRainbow(canvas, RATIO);
			drawOverlay(canvas);
			
			StackPane root = new StackPane(canvas);
			Scene scene = new Scene(root, WIDTH, HEIGHT);
			
			primaryStage.setTitle("DST: Drawing Shapes Tab");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Circle first, hexagon second
	public static void drawInscribedHex(Canvas canvas, double ratio) {
		if (ratio < 0) return;
		
		GraphicsContext GC = canvas.getGraphicsContext2D();
		double WIDTH = canvas.getWidth();
		double HEIGHT = canvas.getHeight();
		
		double radius = Math.min(WIDTH, HEIGHT) * ratio / 2;
		while (radius > MIN) {
			MyCircle c = new MyCircle(WIDTH / 2, HEIGHT / 2, radius, MyColor.randomColor());
			MyPolygon h = new MyPolygon(WIDTH / 2, HEIGHT / 2, radius, MyColor.randomColor());
			c.draw(GC);
			h.draw(GC);
			//System.out.println(c);
			//System.out.println(h);
			radius = h.getApothem();
		}
	}
	
	// Hexagon first, circle second
	public static void drawInscribedCircle(Canvas canvas, double ratio) {
		if (ratio < 0) return;
		
		GraphicsContext GC = canvas.getGraphicsContext2D();
		double WIDTH = canvas.getWidth();
		double HEIGHT = canvas.getHeight();
		
		double radius = Math.min(WIDTH, HEIGHT) * ratio / 2;
		while (radius > MIN) {
			MyPolygon h = new MyPolygon(WIDTH / 2, HEIGHT / 2, radius, MyColor.randomColor());
			MyCircle c = new MyCircle(WIDTH / 2, HEIGHT / 2, radius = h.getApothem(), MyColor.randomColor());
			h.draw(GC);
			c.draw(GC);
			//System.out.println(h);
			//System.out.println(c);
		}
	}
	
	public static void drawRainbow(Canvas canvas, double ratio) {
		if (ratio < 0) return;
		
		Color rainbow[] = {
			MyColor.RED.getColor(),
			MyColor.ORANGE.getColor(),
			MyColor.YELLOW.getColor(),
			MyColor.GREEN.getColor(),
			MyColor.BLUE.getColor(),
			MyColor.PURPLE.getColor()
		};
		
		GraphicsContext GC = canvas.getGraphicsContext2D();
		double WIDTH = canvas.getWidth();
		double HEIGHT = canvas.getHeight();
		
		int i = 0;
		double radius = Math.min(WIDTH, HEIGHT) * ratio / 2;
		
		MyPolygon border = new MyPolygon(WIDTH / 2, HEIGHT / 2, radius + 2, MyColor.BLACK.getColor());
		border.draw(GC);
		
		while (radius > MIN) {
			MyPolygon h = new MyPolygon(WIDTH / 2, HEIGHT / 2, radius, rainbow[(i++ % (rainbow.length - 1))]);
			MyCircle c = new MyCircle(WIDTH / 2, HEIGHT / 2, radius = h.getApothem(), rainbow[(i++ % (rainbow.length - 1))]);
			h.draw(GC);
			c.draw(GC);
			//System.out.println(h);
			//System.out.println(c);
		}
	}
	
	// Draws a criss cross and border
	public static void drawOverlay(Canvas canvas) {
		GraphicsContext GC = canvas.getGraphicsContext2D();
		double WIDTH = canvas.getWidth();
		double HEIGHT = canvas.getHeight();
		
		GC.strokeRect(0, 0, WIDTH, HEIGHT);
		MyLine criss = new MyLine(WIDTH, HEIGHT);
		MyLine cross = new MyLine(0, HEIGHT, WIDTH, 0);
		criss.draw(GC);
		cross.draw(GC);
		//System.out.println(criss);
		//System.out.println(cross);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
