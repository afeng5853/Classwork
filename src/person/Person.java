package person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Person {
	private String firstName;
	private String lastName;
	private String dob;
	private int ssn;
	
	public Person(String firstName, String lastName, String dob, int ssn) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.dob = dob;
		this.ssn = ssn;
	}
	
	public abstract String getGreeting();
	
	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}
	
	public int calculateAge() throws ParseException {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		Date dobDate = sdf.parse(dob);
		double milliBetweenDates = now.getTime() - dobDate.getTime();
		return (int) (milliBetweenDates / 3.154e+10); // conversion rate
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
}
