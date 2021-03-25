package utils;

import game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import game.Story;
import student.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Service
public class DatabaseQueries {

    private final JdbcTemplate jbdcTemplate;

    @Autowired
    public DatabaseQueries(final JdbcTemplate jbdcTemplate) {
        this.jbdcTemplate = jbdcTemplate;
    }

    public Story getStory(final Integer storyId) {
        return this.jbdcTemplate.query("SELECT id, story_text, scrambled_words FROM stories WHERE id = ?", new Object[] {storyId},
                (final ResultSet rs) -> rs.next() ? new Story(rs.getInt(1), rs.getString(2), rs.getString(3)) : null);
    }

    public boolean studentExists(final String studentId) throws DataAccessException {
        Integer count = this.jbdcTemplate.queryForObject("SELECT count(school_student_id) FROM student WHERE school_student_id = ?", new Object[] {studentId}, Integer.class);
        return count != 0;
    }

    public Student getStudent(final String studentId) throws DataAccessException {
        Student student = this.jbdcTemplate.query("SELECT first_name, last_name, school_student_id, teacher_id, story_index, completed_words, score, game_id FROM student WHERE school_student_id = ?", new Object[]{studentId},
                (final ResultSet rs) -> rs.next() ? new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), (Integer[]) rs.getArray(6).getArray(), rs.getInt(7), rs.getInt(8)) : null);
        return student;
    }

    public Game getGame(final int gameId) throws DataAccessException {
        Game game = this.jbdcTemplate.query("SELECT story_ids, started, ended FROM game WHERE id = ?", new Object[]{gameId},
                (final ResultSet rs) -> rs.next() ? new Game((Integer[]) rs.getArray(1).getArray(), rs.getBoolean(2), rs.getBoolean(3)) : null);
        return game;
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
