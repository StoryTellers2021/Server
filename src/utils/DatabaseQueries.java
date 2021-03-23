package utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import game.Story;
import student.Student;

import java.sql.ResultSet;
import java.util.List;

@Service
public class DatabaseQueries {

    private final JdbcTemplate jbdcTemplate;

    @Autowired
    public DatabaseQueries(final JdbcTemplate jbdcTemplate) {
        this.jbdcTemplate = jbdcTemplate;
    }

    public List<Story> queryStories() {
        return this.jbdcTemplate.query("SELECT * FROM stories", (final ResultSet rs, final int rowNum) -> new Story(rs.getString("story_text"), rs.getString("scrambled_words")));
    }

    public boolean studentExists(final String studentId) throws DataAccessException {
        Integer count = this.jbdcTemplate.queryForObject("SELECT count(school_student_id) FROM student WHERE school_student_id = ?", new Object[] {studentId}, Integer.class);
        return count != 0;
    }

    public Student getStudent(final String studentId) throws DataAccessException {
        Student student = this.jbdcTemplate.query("SELECT first_name, last_name, school_student_id, teacher_id, story_index, completed_words FROM student WHERE school_student_id = ?", new Object[]{studentId},
                (final ResultSet rs) -> rs.next() ? new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), (Integer[]) rs.getArray(6).getArray()) : null);
        return student;
    }
}
