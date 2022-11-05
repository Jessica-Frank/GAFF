package com.gaff.demo.models;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alec
 */
@Repository
public class ConnectionsRepository {

    @Autowired
    NamedParameterJdbcTemplate template;

    public Connections getUserById(long user_id) {
        SqlParameterSource namedParameters
                = new MapSqlParameterSource().addValue("user_id", user_id);
        String query = "SELECT * FROM connections WHERE user_id= :user_id";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(Connections.class));
    }
    public void addNewUser(Long user_id, Long game_id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", user_id);
        paramMap.put("game_id", game_id);
        
        String query = "INSERT INTO connections"
                + "(user_id, game_id) "
                + "VALUES(:user_id, :game_id)";

        template.execute(query, paramMap,
                (PreparedStatement ps) -> ps.executeUpdate());
    }
}
