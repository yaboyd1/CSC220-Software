package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	// Canvas Size
	final static double WIDTH = 750;
	final static double HEIGHT = 500;
	
	// How much of the canvas a shape  should take up
	// Adjusts to smallest canvas dimension
	final static double RATIO = .75;
	
	// The minimum radius an shape can have
	final static double MIN = 25;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// Default Values
			int N = 10;
			
			// Input Scene
			Font font = new Font("Arvo", 20);
			
			Label prompt = new Label("How many events would you like to see?");
			prompt.setFont(font);
			
			Button generatePie = new Button("Generate Pie Chart!");
			generatePie.setFont(font);
			
			TextField userInput = new TextField();
			userInput.setFont(font);
			userInput.setFocusTraversable(false);
			userInput.setPromptText("Please enter an integer between 0 and 26");
			userInput.setAlignment(Pos.CENTER);
			
			VBox layout = new VBox(10, prompt, userInput, generatePie);
			layout.setPadding(new Insets(0, WIDTH / 6, 0, WIDTH / 6));
			layout.setAlignment(Pos.CENTER);
			Scene input = new Scene(layout, WIDTH, HEIGHT);
			
			// Pie Chart Scene
			Canvas canvas = new Canvas(WIDTH, HEIGHT);
			GraphicsContext GC = canvas.getGraphicsContext2D();
			
			HistogramAlphaBet test = new HistogramAlphaBet("C:\\Users\\dewan\\OneDrive\\Documents\\GitHub\\SoftwareDesign\\Emma.txt");
			final double DIAMETER = Math.min(WIDTH, HEIGHT) * RATIO;
			MyOval oval = new MyOval(WIDTH/2, HEIGHT/2, DIAMETER, DIAMETER, MyColor.randomColor());
			oval.setCenter(WIDTH/2, HEIGHT/2);
			
			MyPieChart pie = new MyPieChart(oval, test.getFreq(), N, test.getSum());
			pie.draw(GC);
			
			Button goBack = new Button("Go Back!");
			goBack.setFont(font);
			
			StackPane root = new StackPane(canvas, goBack);
			StackPane.setAlignment(goBack, Pos.BOTTOM_LEFT);
			Scene scene = new Scene(root, WIDTH, HEIGHT);
			
			// Switching Scenes
			userInput.setOnAction(e -> {
				try {
					int n = Integer.parseInt(userInput.getText());
					if (n < 0 || n > 26)
						throw new NumberFormatException();
					/* Pressing "Enter" on the text field will randomly make new colors
					if (n == pie.getCount()) {
						primaryStage.setScene(scene);
						return;
					} 
					*/
					pie.setCount(n);
					GC.clearRect(0, 0, WIDTH, HEIGHT);
					pie.draw(GC);
					primaryStage.setScene(scene);
				} catch (NumberFormatException exp) {
					userInput.clear();
					userInput.setPromptText(userInput.getPromptText() + "!");
				}
			});
			
			generatePie.setOnAction(e -> {
				try {
					int n = Integer.parseInt(userInput.getText().strip());
					if (n < 0 || n > 26)
						throw new NumberFormatException();
					if (n == pie.getCount()) {
						primaryStage.setScene(scene);
						return;
					}
					pie.setCount(n);
					GC.clearRect(0, 0, WIDTH, HEIGHT);
					pie.draw(GC);
					primaryStage.setScene(scene);
				} catch (NumberFormatException exp) {
					userInput.clear();
					userInput.setPromptText(userInput.getPromptText() + "!");
				}
			});
			
			goBack.setOnAction(e -> {
				userInput.setPromptText(userInput.getPromptText().replaceAll("!", ""));
				userInput.setFocusTraversable(true);
				primaryStage.setScene(input);
			});
			
			// Initial Scene
			primaryStage.setTitle("DST: Drawing Shapes Tab");
			primaryStage.setScene(input);
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
