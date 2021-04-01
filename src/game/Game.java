<<<<<<< HEAD
package Game;
import Student.Student;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
	final int totalWords;
	private Student[] studentList;
	private int[] scoreList;
	private int[] unScrambled;
	private ArrayList[] scrambled_index;
	private Story story;
	
	public Game(Student[] students, Story story) {
		this.studentList = students;
		this.story = story;
		this.scoreList = new int[students.length];
		this.unScrambled = new int[students.length];
		
		this.totalWords = story.getScrambledWordIndexes().length;
		
		for(int i = 0; i < students.length; i++) {
			this.scoreList[i] = 0;
			this.unScrambled[i] = 0;
			this.scrambled_index[i] = (ArrayList) Arrays.asList(this.story.getScrambledWordIndexes());
		}
=======
package game;
import com.fasterxml.jackson.annotation.JsonProperty;
import student.Student;
import utils.DatabaseStaticHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

	public final int gameId;
	private List<Integer> storyIds;
	@JsonProperty("stories")
	private final List<Story> stories;
	@JsonProperty("started")
	private boolean started;
	@JsonProperty("ended")
	private boolean ended;

	public Game(final int gameId, final Integer[] storyIds, final boolean started, final boolean ended) {
		this.gameId = gameId;
		this.storyIds = new ArrayList<>(Arrays.asList(storyIds));
		this.stories = new ArrayList<>(storyIds.length);
		for(final Integer storyId : storyIds)
			this.stories.add(DatabaseStaticHandler.getStory(storyId));
		this.started = started;
>>>>>>> tim
	}

	public Story getStory(final int storyIndex) {
		return this.started ? this.stories.get(storyIndex) : null;
	}

	public boolean hasStarted() {
		return this.started;
	}
	public boolean hasEnded() {
		return this.ended;
	}
<<<<<<< HEAD
	
	public void addSolved(int index, int s_index, int score) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.studentList.length) {
			throw new IndexOutOfBoundsException("addSolvedUnscrambled: Invalid student index at " + index + "!");
		}
		
		if(score >= 0) {
			this.unScrambled[index] += 1;
			this.scoreList[index] += score;
			this.scrambled_index[index].remove(s_index);
		}
=======

	public boolean isLastStory(final int studentStoryIndex) {
		return studentStoryIndex >= this.stories.size() - 1;
	}

	public boolean storyIsFinished(final int studentStoryIndex, final int studentSolvedWordCount) {
		return studentSolvedWordCount >= this.getStory(studentStoryIndex).getScrambledWordIndexes().length;
>>>>>>> tim
	}

	public boolean studentIsFinished(final Student student) {
		return this.isLastStory(student.getStoryIndex()) && this.storyIsFinished(student.getStoryIndex(), student.getSolvedWordCount());
	}

	public boolean scoreSolution(final Student student, final String studentSolution, final int solvableWordIndex) throws IndexOutOfBoundsException {
		final int studentStoryIndex = student.getStoryIndex();
		final int scoreAdd = this.getStory(studentStoryIndex).scoreSolution(studentSolution, solvableWordIndex);
		if(scoreAdd > 0){
			student.addToScore(scoreAdd);
			student.addSolvedWordIndex(solvableWordIndex);
			if(this.storyIsFinished(studentStoryIndex, student.getSolvedWordCount()) && !this.isLastStory(studentStoryIndex))
				student.advanceStory();
			return true;
		}
		return false;
	}
<<<<<<< HEAD
	
	public int[] getScrambledIndexLeft(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.studentList.length) {
			throw new IndexOutOfBoundsException("getPercentSolved: Invalid student index at " + index + "!");
		}
		int[] temp = new int[this.scrambled_index[index].size()];
		for(int i = 0; i < this.scrambled_index[index].size(); i++) {
			temp[i] = (int) this.scrambled_index[index].get(i);
		}
		
		return temp;
	}
		
=======

	public void addStory(final Story story) {
		this.storyIds.add(story.getId());
		this.stories.add(story);
	}

	public Integer[] getStoryIds() {
		return this.storyIds.toArray(new Integer[this.storyIds.size()]);
	}

>>>>>>> tim
}
