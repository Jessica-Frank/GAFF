package com.gaff.demo.models;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alex Wesley
 */
@Repository
public class ActionRepository {

    @Autowired
    NamedParameterJdbcTemplate template;
    
     public void addAction(String activity, String userName) {
         Date day = new Date();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userName", userName);
        paramMap.put("activity", activity);
        paramMap.put("day", day);

        String query = "INSERT INTO actions"
                + "(user_Name, activity, day)"
                + "VALUES(:userName, :activity, :day)";

        template.execute(query, paramMap,
                (PreparedStatement ps) -> ps.executeUpdate());
    }
     
     public List<Actions> getAllActions() {
        String query = "SELECT id, user_name, activity, day FROM actions ORDER BY day DESC";
        return template.query(query,
                (result, rowNum)
                -> new Actions(
                        result.getLong("id"),
                        result.getString("user_name"),
                        result.getString("activity"),
                        result.getDate("day")
                )
        );
    }
}
