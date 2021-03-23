package utils.unittests;
import static org.junit.jupiter.api.Assertions.*;

import game.Story;
import student.Student;

import org.junit.jupiter.api.Test;

import game.Game;

class TestGame {

	@Test
	public void testgetScore() {
		Student john = new Student("John", "Lennon", "JL0746", 0, 0, new Integer[]{});
		Student paul = new Student("Paul", "Mccartney", "PM0923", 0, 0, new Integer[]{});
		Student george = new Student("George", "Harrison", "GH0233",0,  0, new Integer[]{});
		Student ringo = new Student("Ringo", "Starr", "RS0873", 0, 0, new Integer[]{});
		
		Student[] students = {john, paul, george, ringo};
		
		Story story = new Story("This is just a test story");
		
		Game g = new Game(students, story);
		
		assertEquals(0, g.getScore(0));
		assertEquals(0, g.getScore(1));
		assertEquals(0, g.getScore(2));
		assertEquals(0, g.getScore(3));
		
		g.addSolved(0, 20);
		assertEquals(20, g.getScore(0));
	}
	
	@Test
	public void testaddSolved() {
		Student john = new Student("John", "Lennon", "JL0746", 0, 0, new Integer[]{});
		Student paul = new Student("Paul", "Mccartney", "PM0923", 0, 0, new Integer[]{});
		Student george = new Student("George", "Harrison", "GH0233", 0, 0, new Integer[]{});
		Student ringo = new Student("Ringo", "Starr", "RS0873", 0, 0, new Integer[]{});
		
		Student[] students = {john, paul, george, ringo};
		
		int[] index = {0,1,2,3,4};
		Story story = new Story("This is just a test", index);
		
		Game g = new Game(students, story);
		
		g.addSolved(0, 10);
		g.addSolved(2, 5);
		
		assertEquals(10, g.getScore(0));
		assertEquals(0, g.getScore(1));
		assertEquals(5, g.getScore(2));
		assertEquals(0, g.getScore(3));
		
		assertEquals(20.0, g.getPercentSolved(0));
		assertEquals(0.0, g.getPercentSolved(1));
		assertEquals(20.0, g.getPercentSolved(2));
		
		g.addSolved(0, 1);
		assertEquals(11, g.getScore(0));
		assertEquals(40.0, g.getPercentSolved(0));	
		
		g.addSolved(0, -1);
		assertEquals(11, g.getScore(0));
	}
	
	@Test
	public void testWordsLeft() {
		Student john = new Student("John", "Lennon", "JL0746", 0, 0, new Integer[]{});
		Student paul = new Student("Paul", "Mccartney", "PM0923", 0, 0, new Integer[]{});
		Student george = new Student("George", "Harrison", "GH0233", 0, 0, new Integer[]{});
		Student ringo = new Student("Ringo", "Starr", "RS0873", 0, 0, new Integer[]{});
		
		Student[] students = {john, paul, george, ringo};
		
		int[] index = {0,1,2,3,4};
		Story story = new Story("This is just a test", index);
		
		Game g = new Game(students, story);
		
		g.addSolved(0, 10);
		g.addSolved(2, 5);
		
		assertEquals(4, g.wordsLeft(0));
		assertEquals(5, g.wordsLeft(1));
		assertEquals(4, g.wordsLeft(2));
		
		g.addSolved(0, 2);
		assertEquals(3, g.wordsLeft(0));
	}
	
	@Test
	public void testWordsUnscrambled() {
		Student john = new Student("John", "Lennon", "JL0746", 0, 0, new Integer[]{});
		Student paul = new Student("Paul", "Mccartney", "PM0923", 0, 0, new Integer[]{});
		Student george = new Student("George", "Harrison", "GH0233", 0, 0, new Integer[]{});
		Student ringo = new Student("Ringo", "Starr", "RS0873", 0, 0, new Integer[]{});
		
		Student[] students = {john, paul, george, ringo};
		
		int[] index = {0,1,2,3,4};
		Story story = new Story("This is just a test", index);
		
		Game g = new Game(students, story);
		
		g.addSolved(0, 10);
		g.addSolved(2, 5);
		
		assertEquals(1, g.wordsUnScrambled(0));
		assertEquals(0, g.wordsUnScrambled(1));
		assertEquals(1, g.wordsUnScrambled(2));
		
		g.addSolved(0, 2);
		assertEquals(2, g.wordsUnScrambled(0));
	}
	

}
