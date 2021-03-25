package student;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import game.Game;
import game.Stories;
import game.Story;
import utils.DatabaseStaticHandler;

import java.util.Arrays;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Student {

	public final String firstName;
	public final String lastName;
	public final String schoolStudentId;
	public final int teacherId;
	private int storyIndex;
	private List<Integer> solvedWords;
	private int score;
	private final Game game;

	public Student(final String firstName, final String lastName, final String schoolStudentId, final int teacherId, final int storyIndex, final Integer[] solvedWords, final int score, final int gameId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.schoolStudentId = schoolStudentId;
		this.teacherId = teacherId;
		this.storyIndex = storyIndex;
		this.solvedWords = Arrays.asList(solvedWords);
		this.score = score;
		this.game = DatabaseStaticHandler.getGame(gameId);
	}

	@JsonProperty("firstName")
	public String getFirstName() {
		return this.firstName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return this.lastName;
	}

	public int getTeacherId() {
		return this.teacherId;
	}

	@JsonProperty("solvedWords")
	public List<Integer> getSolvedWords() {
		return this.solvedWords;
	}

	@JsonProperty("score")
	public int getScore() {
		return this.score;
	}

	@JsonProperty("gameStarted")
	public boolean gameStarted() {
		return this.game.hasStarted();
	}

	@JsonProperty("gameEnded")
	public boolean gameEnded() {
		return this.game.hasEnded();
	}

	@JsonProperty("story")
	public Story getCurrentStory() {
		return this.game.getStory(this.storyIndex);
	}
}
