package utils;

import game.Game;
import game.Story;
import student.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseStaticHandler {

    private static DatabaseQueries querier;
    private static final Map<Integer, Story> storyCache = new HashMap<>();
    private static final Map<Integer, Game> gameCache = new HashMap<>();
    private static final Map<String, Student> studentCache = new HashMap<>();

    static void init(final DatabaseQueries databaseQuerier) {
        querier = databaseQuerier;
    }

    public static final Story getStory(final Integer storyId) {
        Story story = storyCache.get(storyId);
        if(story == null) {
            story = querier.getStory(storyId);
            if(story == null)
                return null;
            storyCache.put(storyId, story);
        }
        return story;
    }

    public static final boolean studentIdIsValid(final String studentId) {
        return studentCache.containsKey(studentId) || querier.studentExists(studentId);
    }

    public static final Game getGame(final int gameId) {
        Game game = gameCache.get(gameId);
        if(game == null) {
            game = querier.getGame(gameId);
            if(game == null)
                return null;
            gameCache.put(gameId, game);
        }
        return game;
    }

    public static final Student getStudent(final String studentId) {
        Student student = studentCache.get(studentId);
        if(student == null) {
            student = querier.getStudent(studentId);
            if(student == null)
                return null;
            studentCache.put(studentId, student);
        }
        return student;
    }

}
