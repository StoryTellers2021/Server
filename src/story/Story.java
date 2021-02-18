package story;

import java.util.Arrays;
import java.util.Random;

public class Story {
	
	private final String[] words;
	private final int wordCount;
	private final int storyCharLength;
	private int[] scrambledWordIndexes = new int[0];
	private String[] scrambledWords = new String[0];
	private int scrambledWordCount = 0;
	
	public Story(final String story) {
		this.words = story.split(" ");
		this.wordCount = words.length;
		this.storyCharLength = story.length();
	}
	
	public Story(final String story, final int[] scrambledWordIndexes) throws IndexOutOfBoundsException {
		this(story);
		this.scrambleWordsAt(scrambledWordIndexes);
	}
	
	public void scrambleWordsAt(final int[] scrambledWordIndexes) throws IndexOutOfBoundsException {
		Arrays.sort(scrambledWordIndexes); // getUnsolvedStory needs this to be in ascending order.
		final int wordCount = this.wordCount, scrambledWordCount = scrambledWordIndexes.length;
		String[] scrambledWords = new String[scrambledWordCount];
		for(int scrambledWordIndexesIndex = 0; scrambledWordIndexesIndex < scrambledWordCount; scrambledWordIndexesIndex++) {
			final int wordIndex = scrambledWordIndexes[scrambledWordIndexesIndex];
			if(wordIndex < 0 || wordIndex >= wordCount) {
				throw new IndexOutOfBoundsException("scrambleWordsAt: Invalid word index of " + wordIndex + '!');
			}
			scrambledWords[scrambledWordIndexesIndex] = scramble(this.words[wordIndex]);
		}
		this.scrambledWordIndexes = scrambledWordIndexes;
		this.scrambledWords = scrambledWords;
		this.scrambledWordCount = scrambledWordCount;
	}
	
	public int scoreSolution(final String solution, final int wordIndex) throws IndexOutOfBoundsException {
		final int scrambledWordCount = this.scrambledWordCount;
		if(wordIndex < 0 || wordIndex >= scrambledWordCount) {
			throw new IndexOutOfBoundsException("scoreSolution: Invalid word index of " + wordIndex + '!');
		}
		return this.words[this.scrambledWordIndexes[wordIndex]].equals(solution) ? 1 : 0;
	}
	
	public String getUnsolvedStory() {
		final int scrambledWordCount = this.scrambledWordCount;
		if(scrambledWordCount == 0)
			return this.getSolvedStory();
		final int wordCount = this.wordCount;
		final StringBuilder s = new StringBuilder(this.storyCharLength);
		int scrambledWordIndexesIndex = 0;
		int scrambledWordIndex = this.scrambledWordIndexes[scrambledWordIndexesIndex];
		String word;
		for(int wordIndex = 0; wordIndex < wordCount; wordIndex++) {
			if(scrambledWordIndex == wordIndex) {
				word = this.scrambledWords[scrambledWordIndexesIndex];
				if(++scrambledWordIndexesIndex >= scrambledWordCount)
					scrambledWordIndex = -1;
				else scrambledWordIndex = this.scrambledWordIndexes[scrambledWordIndexesIndex];
			} else word = this.words[wordIndex];
			s.append(wordIndex == wordCount - 1 ? word : word + ' ');
		}
		return s.toString();
	}
	
	public String getSolvedStory() {
		final StringBuilder s = new StringBuilder(this.storyCharLength);
		final int wordCount = this.wordCount;
		for(int wordIndex = 0; wordIndex < wordCount; wordIndex++) {
			final String word = this.words[wordIndex];
			s.append(wordIndex == wordCount - 1 ? word : word + ' ');
		}
		return s.toString();
	}
	
	public int[] getScrambledWordIndexes() {
		return this.scrambledWordIndexes;
	}
	
	private static String scramble(String word) {
		int len = word.length();
		char[] parse = new char[len];
		int[] index = new int[len]; 
		while(true) {
			String ret = "";
			word.getChars(0, len, parse,0);
			Random rand = new Random();
			for(int i = 0; i<len; i++) {
				index[i] = -1;
			}
			for(int i = 0; i<len; i++) {
				int random = rand.nextInt(len);
				while(index[random] != -1 ) {
					random = rand.nextInt(len);
				}
				index[random] = i; 
			}
			for(int i = 0; i<len; i++) {
				ret = ret + parse[index[i]];
			}
			if(!(word.equals(ret)))
				return ret;
		}
	}

}
