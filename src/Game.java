public class Game {
	final int totalWords;
	private Student[] studentList;
	private int[] scoreList;
	private int[] unScrambled;
	private Story story;
	
	public Game(Student[] students, Story story) {
		this.studentList = students;
		this.story = story;
		this.scoreList = new int[students.size()];
		this.unScrambled = new int[students.size()];
		
		this.totalWords = story.getScramledWordIndexes().length;
		for(int i = 0; i < students.size(); i++) {
			this.scoreList[i] = 0;
			this.unScrambled[i] = 0;
		}
	}
	
	public int getScore(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.studentList.size()) {
			throw new IndexOutOfBoundsException("getScore: Invalid student index at " + index + "!");
		}
		return this.scoreList[index];
	}
	
	public int wordsUnScrambled(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.studentList.size()) {
			throw new IndexOutOfBoundsException("wordUnScrambled: Invalid student index at " + index + "!");
		}
		return this.unScrambled[index];
	}
	
	public int wordsLeft(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.studentList.size()) {
			throw new IndexOutOfBoundsException("wordsLeft: Invalid student index at " + index + "!");
		}
		return this.totalWords - this.wordsUnScrambled(index);
	}
	
	public void addScore(int index, int score) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.studentList.size()) {
			throw new IndexOutOfBoundsException("addScore: Invalid student index at " + index + "!");
		}
		this.scoreList[index] += score;
	}
	
	public void addSolvedUnscrambled(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.studentList.size()) {
			throw new IndexOutOfBoundsException("addSolvedUnscrambled: Invalid student index at " + index + "!");
		}
		this.unScrambled[index] += 1;
	}
	
	public double getPercentSolved(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.studentList.size()) {
			throw new IndexOutOfBoundsException("getPercentSolved: Invalid student index at " + index + "!");
		}
		return this.unScrambled[index] * 100.0/ this.totalWords;
	}
}
