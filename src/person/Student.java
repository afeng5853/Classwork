package person;

public class Student extends Person {
	private double gpa;
	private String school;
	private long osis;
	
	public Student(String firstName, String lastName, String dob, int ssn, double gpa, String school, long osis) {
		super(firstName, lastName, dob, ssn);
		this.setGpa(gpa);
		this.setSchool(school);
		this.setOsis(osis);
	}

	@Override
	public String getGreeting() {
		// TODO Auto-generated method stub
		return "Hi I am the student named " + getFirstName() + " " + getLastName();
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public long getOsis() {
		return osis;
	}

	public void setOsis(long osis) {
		this.osis = osis;
	}

}
