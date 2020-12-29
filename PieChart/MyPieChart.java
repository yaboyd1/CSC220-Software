package application;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MyPieChart {
	// PRIVATE MEMBER VARIABLES
	private MyOval pie;
	private int count;
	private Map<Character, Integer> freq;
	private int sum;

	// CONSTRUCTORS
	public MyPieChart(MyOval oval, HashMap<Character, Integer> events, int count) {
		freq = sort(events);
		this.count = count;
		pie = new MyOval(oval);
		setSum();
	}

	public MyPieChart(MyOval oval, HashMap<Character, Integer> events, int count, int sum) {
		freq = sort(events);
		this.count = count;
		pie = new MyOval(oval);
		this.sum = sum;
	}

	// MEMBER FUNCTIONS
	public void draw(GraphicsContext GC) {
		// Constants
		final double START_ANGLE = 90;
		final double ROTATION = -1;
		final int FONT_SIZE = 16;
		final double space = FONT_SIZE + 20;
		final MyColor pieColor = MyColor.GREY;
		final MyColor textColor = MyColor.BLACK;
		final Font font = new Font("Arvo", FONT_SIZE);

		// Background Pie Chart
		pie.setColor(pieColor.getColor());
		pie.draw(GC);

		// Temporary Variables
		MyArc arc;
		Map.Entry<Character, Integer> item;
		double shift;
		double r = 0;
		double mid;
		double angle = START_ANGLE;
		double total = 0;

		Iterator<Map.Entry<Character, Integer>> itr = freq.entrySet().iterator();
		for (int i = 0; itr.hasNext() && i < count; ++i) {
			// Increment
			item = itr.next();
			shift = ROTATION * item.getValue() / sum * 360;
			total += item.getValue() / (double)sum;

			// Calculate radius and angles
			mid = angle + (shift / 2);
			r = pie.getRadius(mid + 180) + space;
			mid = Math.toRadians(mid);

			// Draw arc
			arc = new MyArc(pie, angle, shift, MyColor.randomColor());
			angle += shift;
			arc.draw(GC);

			// Draw text
			GC.setFill(textColor.getColor());
			GC.setTextAlign(TextAlignment.CENTER);
			GC.setFont(font);
			GC.fillText(String.format("%s, %.4f", item.getKey().toString(), item.getValue() / (double)sum),
					pie.getX() + pie.getWidth() / 2 + r * Math.cos(mid),
					pie.getY() + pie.getHeight() / 2 - r * Math.sin(mid));
		}

		// Edge Case
		if (itr.hasNext()) {
			item = itr.next();
			mid = angle + (360 + START_ANGLE - angle) / 2;
			r = pie.getRadius(mid + 180) + space;

			GC.setFill(textColor.getColor());
			GC.setTextAlign(TextAlignment.CENTER);

			if (mid >= 90 + 30 && mid <= 270 - 30) {
				GC.setTextAlign(TextAlignment.RIGHT);
				r -= space / 2;
			}
			mid = Math.toRadians(mid);
			GC.fillText(String.format("%s, %.4f", "All other letters", 1 - total),
					pie.getX() + pie.getWidth() / 2 + r * Math.cos(mid),
					pie.getY() + pie.getHeight() / 2 - r * Math.sin(mid));
		}
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();

		for (Character c : freq.keySet())
			output.append(String.format("%s: %d %n", c, freq.get(c)));

		return output.toString();
	}

	// HELPER FUNCTIONS
	public static Map<Character, Integer> sort(HashMap<Character, Integer> unsorted) {
		return unsorted.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						Map.Entry::getValue,
						(e1, e2) -> e1,
						LinkedHashMap::new));
	}

	private void setSum() {
		for (Character c : freq.keySet())
			sum += freq.get(c);
	}

	// GETTERS AND SETTERS
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
