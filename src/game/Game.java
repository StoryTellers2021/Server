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
