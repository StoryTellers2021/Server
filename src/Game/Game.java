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
	}
	
	public int getScore(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.studentList.length) {
			throw new IndexOutOfBoundsException("getScore: Invalid student index at " + index + "!");
		}
		return this.scoreList[index];
	}
	
	public int wordsUnScrambled(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.studentList.length) {
			throw new IndexOutOfBoundsException("wordUnScrambled: Invalid student index at " + index + "!");
		}
		return this.unScrambled[index];
	}
	
	public int wordsLeft(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.studentList.length) {
			throw new IndexOutOfBoundsException("wordsLeft: Invalid student index at " + index + "!");
		}
		return this.totalWords - this.wordsUnScrambled(index);
	}
	
	public void addSolved(int index, int s_index, int score) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.studentList.length) {
			throw new IndexOutOfBoundsException("addSolvedUnscrambled: Invalid student index at " + index + "!");
		}
		
		if(score >= 0) {
			this.unScrambled[index] += 1;
			this.scoreList[index] += score;
			this.scrambled_index[index].remove(s_index);
		}
	}
	
	public double getPercentSolved(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.studentList.length) {
			throw new IndexOutOfBoundsException("getPercentSolved: Invalid student index at " + index + "!");
		}
		return this.unScrambled[index] * 100.0/ this.totalWords;
	}
	
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
		
}
