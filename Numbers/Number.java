import java.util.Scanner;
import java.util.Random;

public class Number {

	private String title;
	private double id;

	public Number(String title, double id) {
		this.title = title;
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public double getId() {
		return id;
	}

	public static void main(String[] args) {
		Number A[] = new Number[2];

		Scanner input = new Scanner(System.in);
		Random gen = new Random();

		String title = null;

		for (int i = 0; i < A.length; ++i) {
			System.out.printf("%10s ", "Name:");
			title = input.next();

			A[i] = new Number(title, (double)gen.nextInt(500) + 500);
		}

		for (Number i : A) {
			System.out.printf("%10s %s %n", "Name:", i.getTitle());
			System.out.printf("%10s %f %n", "ID:", i.getId());
		}
	}
}