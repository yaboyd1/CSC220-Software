package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	// Canvas Size
	final static double WIDTH = 750;
	final static double HEIGHT = 500;

	// How much of the canvas a shape  should take up
	// Adjusts to smallest canvas dimension in drawInscribedHex()
	final static double RATIO = .7;

	// The minimum radius an inscribed shape can have
	final static double MIN = 25;

	@Override
	public void start(Stage primaryStage) {
		try {
			Canvas canvas = new Canvas(WIDTH, HEIGHT);
			GraphicsContext GC = canvas.getGraphicsContext2D();

			/*
			 * MyRectangle rect = new MyRectangle(0, HEIGHT / 2, WIDTH, HEIGHT,
			 * MyColor.BLUE.getColor()); rect.draw(GC);
			 *
			 * MyPolygon hex = new MyPolygon(WIDTH / 2, HEIGHT / 2, 100,
			 * MyColor.RED.getColor()); hex.draw(GC);
			 *
			 * for (MyPoint p : MyShapeInterface.overlapMyShapes(rect, hex)) p.draw(GC);
			 */

			drawRainbowOval(canvas, RATIO);

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

	// Rectangle first, oval second
	public static void drawInsribedOval(Canvas canvas, double ratio) {
		if (ratio < 0) return;

		// Width and Height of Canvas
		GraphicsContext GC = canvas.getGraphicsContext2D();
		final double WIDTH = canvas.getWidth();
		final double HEIGHT = canvas.getHeight();

		// Angle and Center of all ovals and rectangles
		final double angle = new MyLine(0, 0, WIDTH, HEIGHT).get_xAngle();
		final MyPoint center = new MyPoint(WIDTH / 2, HEIGHT / 2);

		// Temporary variables
		MyOval oval;
		MyRectangle rect;
		double r;

		// Draw inscribed ovals inside rectangles
		double w = WIDTH * ratio;
		double h = HEIGHT * ratio;
		while (Math.min(w, h) > MIN * 2) {
			oval = new MyOval(0, 0, w, h, MyColor.randomColor());
			oval.setCenter(center);

			rect = oval.getMyBoundingRectangle();
			rect.setColor(MyColor.randomColor());

			rect.draw(GC);
			oval.draw(GC);

			r = oval.getRadius(angle);

			w = r * Math.cos(Math.toRadians(angle)) * 2;
			h = r * Math.sin(Math.toRadians(angle)) * 2;
		}
	}

	// Draw Rainbow Ovals and Rectangles
	public void drawRainbowOval(Canvas canvas, double ratio) {
		if (ratio < 0) return;

		// Chosen rainbow colors
		final Color rainbow[] = {
			MyColor.ORANGE.getColor(),
			MyColor.RED.getColor(),
			MyColor.GREEN.getColor(),
			MyColor.YELLOW.getColor(),
			MyColor.PURPLE.getColor(),
			MyColor.BLUE.getColor()
		};

		// Width and Height of Canvas
		GraphicsContext GC = canvas.getGraphicsContext2D();
		final double WIDTH = canvas.getWidth();
		final double HEIGHT = canvas.getHeight();

		// Angle and Center of all ovals and rectangles
		final double angle = new MyLine(0, 0, WIDTH, HEIGHT).get_xAngle();
		final MyPoint center = new MyPoint(WIDTH / 2, HEIGHT / 2);

		// Temporary variables
		MyOval oval;
		MyRectangle rect;
		double r;
		int index = 0;

		// Draw inscribed ovals inside rectangles
		double w = WIDTH * ratio;
		double h = HEIGHT * ratio;
		while (Math.min(w, h) > MIN * 2) {
			oval = new MyOval(0, 0, w, h, rainbow[(index++ % (rainbow.length))]);
			oval.setCenter(center);

			rect = oval.getMyBoundingRectangle();
			rect.setColor(rainbow[(index++ % (rainbow.length))]);

			rect.draw(GC);
			oval.draw(GC);

			System.out.println(rect);
			System.out.println(oval);

			r = oval.getRadius(angle);

			w = r * Math.cos(Math.toRadians(angle)) * 2;
			h = r * Math.sin(Math.toRadians(angle)) * 2;
		}
	}

	// Circle first, hexagon second
	public static void drawInscribedHex(Canvas canvas, double ratio) {
		if (ratio < 0) return;

		// Width and Height of Canvas
		GraphicsContext GC = canvas.getGraphicsContext2D();
		final double WIDTH = canvas.getWidth();
		final double HEIGHT = canvas.getHeight();

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

	// Draws hexagons in a rainbow
	public static void drawRainbow(Canvas canvas, double ratio) {
		if (ratio < 0) return;

		// Chosen Rainbow Colors
		Color rainbow[] = {
			MyColor.RED.getColor(),
			MyColor.ORANGE.getColor(),
			MyColor.YELLOW.getColor(),
			MyColor.GREEN.getColor(),
			MyColor.BLUE.getColor(),
			MyColor.PURPLE.getColor()
		};

		// Width and Height of Canvas
		GraphicsContext GC = canvas.getGraphicsContext2D();
		final double WIDTH = canvas.getWidth();
		final double HEIGHT = canvas.getHeight();

		// Temporary Variables
		int index = 0;
		double radius = Math.min(WIDTH, HEIGHT) * ratio / 2;

		// Draw a Simple Border
		MyPolygon border = new MyPolygon(WIDTH / 2, HEIGHT / 2, radius + 2, MyColor.BLACK.getColor());
		border.draw(GC);

		while (radius > MIN) {
			MyPolygon h = new MyPolygon(WIDTH / 2, HEIGHT / 2, radius, rainbow[(index++ % (rainbow.length - 1))]);
			MyCircle c = new MyCircle(WIDTH / 2, HEIGHT / 2, radius = h.getApothem(), rainbow[(index++ % (rainbow.length - 1))]);

			h.draw(GC);
			c.draw(GC);

			//System.out.println(h);
			//System.out.println(c);
		}
	}

	// Draws a Criss Cross and Border
	public static void drawOverlay(Canvas canvas) {
		// Width and Height of Canvas
		GraphicsContext GC = canvas.getGraphicsContext2D();
		final double WIDTH = canvas.getWidth();
		final double HEIGHT = canvas.getHeight();

		// Draw Criss Cross and Border
		GC.strokeRect(0, 0, WIDTH, HEIGHT);
		MyLine criss = new MyLine(WIDTH, HEIGHT);
		//MyLine cross = new MyLine(0, HEIGHT, WIDTH, 0);

		criss.draw(GC);
		//cross.draw(GC);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
