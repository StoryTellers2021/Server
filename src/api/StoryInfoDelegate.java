package api;

import story.Story;
import com.fasterxml.jackson.annotation.JsonValue;

public class StoryInfoDelegate {
	
	private Story story = new Story("Roses are red, Violets are blue, I don't sleep at night, Cause I'm thinking of you", new int[] {0, 3, 8, 11, 13});
	
	@JsonValue
	public Story getStory() {
		return this.story;
	}
}
