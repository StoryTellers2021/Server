package student;
public class Student {

	private final String firstName;
	private final String lastName;
	private final String schoolStudentId;
	private int storyIndex;
	
	public Student(final String firstName, final String lastName, final String schoolStudentId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.schoolStudentId = schoolStudentId;
		this.storyIndex = 0;
	}
}
