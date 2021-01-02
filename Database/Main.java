package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
			userInput.setPromptText("Please enter an integer between 0 and 6");
			userInput.setAlignment(Pos.CENTER);
			
			VBox layout = new VBox(10, prompt, userInput, generatePie);
			layout.setPadding(new Insets(0, WIDTH / 6, 0, WIDTH / 6));
			layout.setAlignment(Pos.CENTER);
			Scene input = new Scene(layout, WIDTH, HEIGHT);
			
			// Pie Chart Scene
			Canvas canvas = new Canvas(WIDTH, HEIGHT);
			GraphicsContext GC = canvas.getGraphicsContext2D();
			
			Database database = new Database();
			final double DIAMETER = Math.min(WIDTH, HEIGHT) * RATIO;
			MyOval oval = new MyOval(WIDTH/2, HEIGHT/2, DIAMETER, DIAMETER, MyColor.randomColor());
			oval.setCenter(WIDTH/2, HEIGHT/2);
			
			MyPieChart pie = new MyPieChart(oval, database.getGrades(), N, database.getTotal());
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
					if (n < 0 || n > 6)
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
					if (n < 0 || n > 6)
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
