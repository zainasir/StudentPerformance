package StudentPerformance;

public class Person {
	private String firstName;
	private String lastName;
	
	public Person(String fName, String lName) {
		super();
		this.firstName = fName;
		this.lastName = lName;
	}
	
	public String name() {
		return lastName() + ", " + firstName();
	}

	public String firstName() {
		return firstName;
	}

	public String lastName() {
		return lastName;
	}
	
}
