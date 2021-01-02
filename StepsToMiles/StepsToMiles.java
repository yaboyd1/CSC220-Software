import java.util.GregorianCalendar;

public class StepsToMiles {

	// PRIVATE MEMBER VARIABLES
	private String name;
	private double feet;
	private double inches;

	// CONSTRUCTORS
	public StepsToMiles() {
		this(null, -1, -1);
	}

	public StepsToMiles(String name, double feet, double inches) {
		this.name = name;
		this.feet = feet;
		this.inches = inches;
	}

	// METHODS
	public String formatAsString(int steps) {
		StringBuilder output = new StringBuilder();

		output.append(String.format("%13s: %s %n", "Name", getName()));
		output.append(String.format("%13s: %,.2f' %,.2f\" %n", "Height", getFeet(), getInches()));
		output.append(String.format("%13s: %,.2f %n", "Stride", strideLength_inches()));
		output.append(String.format("%13s: %,d %n", "Steps", steps));
		output.append(String.format("%13s: %,.2f miles %n", "Walk", miles(steps)));

		return output.toString();
	}

	public String currDate() {
		/* NOTE: Months are 0 indexed but days are not */
		GregorianCalendar calendar = new GregorianCalendar();
		String out = String.format("%s/%s/%s", 
			calendar.get(GregorianCalendar.MONTH) + 1, 
			calendar.get(GregorianCalendar.DAY_OF_MONTH), 
			calendar.get(GregorianCalendar.YEAR));
		return out;
	}

	// HELPER METHODS
	public double height_inches() {
		return feet * 12 + inches;
	}

	public double strideLength_inches() {
		return height_inches() * 0.413;
	}

	public double miles(int steps) {
		return (strideLength_inches() * steps) / (12 * 5280);
	}

	// GETTERS
	public String getName() {
		return name;
	}
	public double getFeet() {
		return feet;
	}
	public double getInches() {
		return inches;
	}

	// SETTERS
	public void setName(String name) {
		this.name = name;
	}
	public void setFeet(double feet) {
		this.feet = feet;
	}
	public void setInches(double inches) {
		this.inches = inches;
	}
}