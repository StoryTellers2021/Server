package utils;

import game.Story;

import java.util.List;

public class DatabaseStaticHandler {
    private static DatabaseQueries querier;
    static void init(DatabaseQueries databaseQuerier) {
        querier = databaseQuerier;
        game.Stories.setStories(fetchAllStories());
    }

    public static List<Story> fetchAllStories() {
        return querier.queryStories();
    }
}
