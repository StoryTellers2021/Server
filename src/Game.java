public class Game {
	final int totalWords;
	private Student[] studentList;
	private int[] scoreList;
	private int[] unScrambled;
	private Story story;
	
	public Game(Student[] students, Story story) {
		this.studentList = students;
		this.story = story;
		this.scoreList = new int[students.length];
		this.unScrambled = new int[students.length];
		
		this.totalWords = story.getScramledWordIndexes().length;
		for(int i = 0; i < students.length; i++) {
			this.scoreList[i] = 0;
			this.unScrambled[i] = 0;
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
			throw new IndexOutOfBoundsException("getScore: Invalid student index at " + index + "!");
		}
		return this.unScrambled[index];
	}
	
	public int wordsLeft(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= this.studentList.length) {
			throw new IndexOutOfBoundsException("getScore: Invalid student index at " + index + "!");
		}
		return this.totalWords - this.wordsUnScrambled(index);
	}
	
}