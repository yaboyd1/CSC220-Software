import java.util.Random;

public class TortoiseAndHare {
	private static final int MAX_MOVES = 100;
	private static final int MAX_ITERATIONS = 2000;
	private static final Random rng = new Random();

	private int iterations = 0;
	private int T = 0;
	private int H = 0;


	// MAIN
	public static void main(String[] args) {
		TortoiseAndHare game = new TortoiseAndHare();
	}

	// CONSTRUCTOR
	public TortoiseAndHare() {
		printStartMsg();

		printPositions();
		while (T < MAX_MOVES && H < MAX_MOVES && iterations++ < MAX_ITERATIONS) {
			simulateTortoiseMove();
			simulateHareMove();
			printPositions();
		}

		printEndMsg();
	}

	// MAIN METHODS
	public void simulateTortoiseMove() {
		int moveType = randomBetween(0, 9);
		switch (moveType) {
			case 0:
				break;
			case 1: case 2: case 3: case 4:
				T += randomBetween(1, 3);
				break;
			case 5: case 6: case 7:
				T -= randomBetween(1, 6);
				break;
			case 8: case 9:
				T += randomBetween(0, 1);
				break;
		}
		if (T < 0) T = 0;
		else if (T > MAX_MOVES) T = MAX_MOVES;
	}

	public void simulateHareMove() {
		int moveType = randomBetween(0, 9);
		switch (moveType) {
			case 0:
				break;
			case 1: case 2: case 3: 
				H += randomBetween(1, 5);
				break;
			case 4: case 5: 
				H -= randomBetween(1, 2);
				break;
			case 6: 
				H -= randomBetween(1, 7);
				break;
			case 7: case 8: case 9:
				H += randomBetween(0, 1);
				break;
		}
		if (H < 0) H = 0;
		else if (H > MAX_MOVES) H = MAX_MOVES;
	}

	// HELPER FUNCTIONS
	private int randomBetween(int min, int max) {
		return rng.nextInt(max - min + 1) + min;
	}

	private void printPositions() {
		System.out.printf("Iteration: %d %n", iterations);

		for (int i = 0; i < MAX_MOVES; ++i) {
			if (T == i && H == i)
				System.out.print("B");
			else if (T == i)
				System.out.print("T");
			else if (H == i)
				System.out.print("H");
			else
				System.out.print(" ");
		}

		System.out.println();
		for (int i = 0; i < MAX_MOVES; ++i)
			System.out.print("-");
		System.out.println();
	}

	private void printStartMsg() {
		System.out.println("On your marks...");
		System.out.println("Get set...");
		System.out.println("Get ready...");
		System.out.println("Go!");
	}

	private void printEndMsg() {
		if (iterations == MAX_ITERATIONS)
			System.out.println("Maximum Iterations!");

		if (T > H) 
			System.out.println("Tortoise wins!");
		else if (T < H)
			System.out.println("Hare wins!");
		else
			System.out.println("Tie!");

		System.out.printf("Iterations: %d", iterations);
	}
}