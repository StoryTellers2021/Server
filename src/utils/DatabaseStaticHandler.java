package utils;

import game.Game;
import game.Story;
import student.Student;

public class DatabaseStaticHandler {

    private static DatabaseQueries querier;

    static void init(final DatabaseQueries databaseQuerier) {
        querier = databaseQuerier;
    }

    public static final Story getStory(final Integer storyId) {
        return querier.getStory(storyId);
    }

    public static final Game getGame(final int gameId) {
        return querier.getGame(gameId);
    }

    public static final Student getStudent(final String studentId) {
        return querier.getStudent(studentId);
    }

}
