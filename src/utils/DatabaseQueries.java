package utils;

import game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import game.Story;
import student.Student;
import teacher.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DatabaseQueries {

    private final JdbcTemplate jbdcTemplate;

    private final Map<Integer, Story> storyCache = new HashMap<>();
    private final Map<Integer, Game> gameCache = new HashMap<>();
    private final Map<String, Student> studentCache = new HashMap<>();
    private final Map<String, Teacher> teacherByCodeCache = new HashMap<>();

    @Autowired
    public DatabaseQueries(final JdbcTemplate jbdcTemplate) {
        this.jbdcTemplate = jbdcTemplate;
    }

    public Story getStory(final Integer storyId) {
        Story story = storyCache.get(storyId);
        if(story == null) {
            story = this.jbdcTemplate.query("SELECT id, story_text, scrambled_words FROM stories WHERE id = ?", new Object[] {storyId},
                    (final ResultSet rs) -> rs.next() ? new Story(rs.getInt(1), rs.getString(2), rs.getString(3)) : null);
            if(story == null)
                return null;
            storyCache.put(storyId, story);
        }
        return story;
    }

    public boolean studentExists(final String studentId) throws DataAccessException {
        if(studentCache.containsKey(studentId))
            return true;
        Integer count = this.jbdcTemplate.queryForObject("SELECT count(school_student_id) FROM student WHERE school_student_id = ?", new Object[] {studentId}, Integer.class);
        return count != 0;
    }

    public Student getStudent(final String studentId) throws DataAccessException {
        Student student = studentCache.get(studentId);
        if(student == null) {
            student = this.jbdcTemplate.query("SELECT first_name, last_name, school_student_id, teacher_id, story_index, completed_words, score, game_id FROM student WHERE school_student_id = ?", new Object[]{studentId},
                    (final ResultSet rs) -> rs.next() ? new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), (Integer[]) rs.getArray(6).getArray(), rs.getInt(7), rs.getInt(8)) : null);
            if(student == null)
                return null;
            studentCache.put(studentId, student);
        }
        return student;
    }

    public Game getGame(final int gameId) throws DataAccessException {
        Game game = gameCache.get(gameId);
        if(game == null) {
            game = this.jbdcTemplate.query("SELECT story_ids, started, ended FROM game WHERE id = ?", new Object[]{gameId},
                    (final ResultSet rs) -> rs.next() ? new Game(gameId, (Integer[]) rs.getArray(1).getArray(), rs.getBoolean(2), rs.getBoolean(3)) : null);
            if(game == null)
                return null;
            gameCache.put(gameId, game);
        }
        return game;
    }

    public Teacher getTeacherByCode(final String teacherCode) throws DataAccessException {
        Teacher teacher = teacherByCodeCache.get(teacherCode);
        if(teacher == null) {
            teacher = this.jbdcTemplate.query("SELECT id, first_name, last_name, game_id FROM teacher WHERE code = ?", new Object[]{teacherCode},
                    (final ResultSet rs) -> rs.next() ? new Teacher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)) : null);
            if(teacher == null)
                return null;
            teacherByCodeCache.put(teacherCode, teacher);
        }
        return teacher;
    }

    public List<Student> getStudentsByTeacherCode(final String teacherCode) {
        final Teacher teacher = this.getTeacherByCode(teacherCode);
        return this.jbdcTemplate.query("SELECT first_name, last_name, school_student_id, teacher_id, story_index, completed_words, score, game_id FROM student WHERE teacher_id = ? AND game_id = ?", new Object[]{teacher.teacherId, teacher.getGame().gameId},
                (final ResultSet rs, int rowNum) -> new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), (Integer[]) rs.getArray(6).getArray(), rs.getInt(7), rs.getInt(8)));
    }

    public Game getGameByTeacherCode(final String teacherCode) {
        return this.getTeacherByCode(teacherCode).getGame();
    }

    public Boolean updateStudentProgress(final String studentId, final int storyIndex, final Integer[] completedWords, final int score) {
        return this.jbdcTemplate.execute("UPDATE users SET story_index = ?, completed_words = ?, score = ? WHERE school_student_id = ?", (final PreparedStatement ps) -> {
            ps.setInt(1, storyIndex);
            ps.setArray(2, ps.getConnection().createArrayOf("integer", completedWords));
            ps.setInt(3, score);
            ps.setString(4, studentId);
            return ps.execute();
        });
    }
}
