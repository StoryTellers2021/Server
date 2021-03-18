package game;

import java.util.List;

public class Stories {
    private static List<Story> allStories;

    private static boolean isValidStoryIndex(final int index) {
        return index < 0 || index >= allStories.size();
    }

    public static void setStories(final List<Story> stories) {
        allStories = stories;
    }

    public static Story getStoryAtIndex(final int index) throws java.lang.IndexOutOfBoundsException {
        if(isValidStoryIndex(index)) {
            throw new java.lang.IndexOutOfBoundsException("getStoryAtIndex: Index " + index + " is invalid!");
        } else {
            return allStories.get(index);
        }
    }
}
