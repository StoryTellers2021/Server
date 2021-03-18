package utils;

import com.fasterxml.jackson.annotation.JsonValue;

public class StoryInfoDelegate {

	private final game.Story story;

	public StoryInfoDelegate(final String storyIndex) throws java.lang.IndexOutOfBoundsException {
		this.story = game.Stories.getStoryAtIndex(Integer.parseInt(storyIndex));
	}

	@JsonValue
	public game.Story getStory() {
		return this.story;
	}
}
