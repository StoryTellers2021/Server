package utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import game.Story;

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

}
