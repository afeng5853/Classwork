package person;

import java.text.ParseException;

public class Runner {

	public static void main(String[] args) throws ParseException {
		Teacher test = new Teacher("Alex", "Feng", "05/21/2000", 207165853, null, "cs", "btech");
		System.out.println(test.calculateAge());
	}
}
