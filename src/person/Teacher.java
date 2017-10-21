package person;

public class Teacher extends Person {
	private Student[][] classes;
	private String speciality;
	private String school;
	
	public Teacher(String firstName, String lastName, String dob, int ssn, Student[][] classes, String speciality, String school) {
		super(firstName, lastName, dob, ssn);
		this.setClasses(classes);
		this.setSpeciality(speciality);
		this.setSchool(school);
	}

	@Override
	public String getGreeting() {
		return "Hi I'm a teacher and my name is " + getFirstName() + " " + getLastName();
	}

	public Student[][] getClasses() {
		return classes;
	}

	public void setClasses(Student[][] classes) {
		this.classes = classes;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

}
