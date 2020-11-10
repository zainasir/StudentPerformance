package StudentPerformance;

public class Student implements Named{
	private Person asPerson;
	private String major;
	private String universityName;
	
	public Student(Person asPerson, String major, String universityName) {
		super();
		this.asPerson = asPerson;
		this.major = major;
		this.universityName = universityName;
	}
	
	@Override
	public String name() {
		return asPerson.name();
	}

	public Person getAsPerson() {
		return asPerson;
	}

	public String getMajor() {
		return major;
	}

	public String getUniversityName() {
		return universityName;
	}
	
	
	
}
