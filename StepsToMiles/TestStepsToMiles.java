import java.util.Scanner;

public class TestStepsToMiles {
	public static void main(String[] args) {

		// Testing Default Constructor and Setter methods
		StepsToMiles johnDoe = new StepsToMiles();
		johnDoe.setName("John Doe");
		johnDoe.setFeet(5);
		johnDoe.setInches(4.5);

		// Prompting User for input
		Scanner input = new Scanner(System.in);

		System.out.printf("%26s: ", "Enter Name");
		String name = input.nextLine();

		System.out.printf("%26s: ", "Enter Height (ft and in)");
		double feet = input.nextDouble();
		double inches = input.nextDouble();

		// Testing Parameterized Constructor
		StepsToMiles user = new StepsToMiles(name, feet, inches);

		// Testing date
		System.out.printf("%n%13s: %s %n %n", "Today's Date", user.currDate());

		// Testing formatted output
		System.out.printf("%s %n", johnDoe.formatAsString(12_345));
		System.out.printf("%s %n", user.formatAsString(1_000));
	}
}