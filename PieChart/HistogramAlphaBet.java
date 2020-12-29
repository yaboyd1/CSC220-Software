package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class HistogramAlphaBet {
	// PRIVATE MEMBER VARIABLES
	private String fileName;
	private HashMap<Character, Integer> freq;
	private int sum = 0;
	
	private static Scanner input;
	
	// CONSTRUCTOR
	public HistogramAlphaBet(String fileName) {
		this.fileName = fileName;
		freq = new HashMap<>();
		calcFreq();
	}
	
	// MEMBER FUNCTIONS
	public void calcFreq() {
		try {
			input = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found!");
			return;
		}
		
		String line;
		while(input.hasNextLine()) {
			line = input.nextLine().replaceAll("[^a-zA-Z]", "").toLowerCase();
			for (int i = 0; i < line.length(); ++i)
				incrementFreq(line.charAt(i));
		}
		input.close();
	}
	
	// GETTERS
	public HashMap<Character, Integer> getFreq() {
		return freq;
	}
	public int getSum() {
		return sum;
	}
	
	// PRIVATE HELPERS
	private void incrementFreq(Character key) {
		freq.putIfAbsent(key, 0);
		freq.put(key, freq.get(key) + 1);
		++sum;
	}
}
