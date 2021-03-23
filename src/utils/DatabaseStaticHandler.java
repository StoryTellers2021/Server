package utils;

import game.Story;
import student.Student;

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

    public static boolean studentIdIsValid(final String studentId) {
        return querier.studentExists(studentId);
    }

    public static Student getStudent(final String studentId) {
        return querier.getStudent(studentId);
    }
}
