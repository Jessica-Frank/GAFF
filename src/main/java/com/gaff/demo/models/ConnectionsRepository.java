package com.gaff.demo.models;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/*
 * These are the sql queries to the connections table
 * Last updated 11/10/2022
 * Author(s): Alec Droegemeier
 */
@Repository
public class ConnectionsRepository {

    @Autowired
    NamedParameterJdbcTemplate template;

    public List<Connections> getUsersByGame(long game_id) {
        SqlParameterSource parameters = new MapSqlParameterSource("game_id", game_id);
        List<Connections> connect = template.query(
                "SELECT * FROM connections WHERE game_id IN (:game_id)",
                parameters,
                (rs, rowNum) -> new Connections(
                        rs.getLong("user_id"),
                        rs.getLong("game_id")));
        return connect;
    }
    
    public List<Connections> getGamesByUser(long user_id) {
        SqlParameterSource parameters = new MapSqlParameterSource("user_id", user_id);
        List<Connections> connect = template.query(
                "SELECT * FROM connections WHERE user_id IN (:user_id)",
                parameters,
                (rs, rowNum) -> new Connections(
                        rs.getLong("user_id"),
                        rs.getLong("game_id")));
        return connect;
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
