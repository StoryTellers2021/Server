package student;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.sql.Array;
import java.sql.SQLException;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.ANY)
public class Student {

	public final String firstName;
	public final String lastName;
	public final String schoolStudentId;
	public final int teacherId;
	private int storyIndex;
	private Integer[] completedWords;
	
	public Student(final String firstName, final String lastName, final String schoolStudentId, final int teacherId, final int storyIndex, final Integer[] completedWords) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.schoolStudentId = schoolStudentId;
		this.teacherId = teacherId;
		this.storyIndex = storyIndex;
		this.completedWords = completedWords;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getTeacherId() {
		return this.teacherId;
	}

	public int getStoryIndex() {
		return storyIndex;
	}

	public Integer[] getCompletedWords() {
		return completedWords;
	}
}
