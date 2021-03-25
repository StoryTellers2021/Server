package game;
import student.Student;
import utils.DatabaseStaticHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

	private List<Integer> storyIds;
	private final List<Story> stories;
	private boolean started;
	private boolean ended;

	public Game(final Integer[] storyIds, final boolean started, final boolean ended) {
		this.storyIds = Arrays.asList(storyIds);
		this.stories = new ArrayList<>(storyIds.length);
		for(final Integer storyId : storyIds)
			this.stories.add(DatabaseStaticHandler.getStory(storyId));
		this.started = started;
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

}
