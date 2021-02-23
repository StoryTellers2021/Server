package api;

import story.Story;
import com.fasterxml.jackson.annotation.JsonValue;

public class StoryInfoDelegate {
	
	private Story story = new Story("JSON is awesome");
	
	@JsonValue
	public Story getStory() {
		return this.story;
	}
}
