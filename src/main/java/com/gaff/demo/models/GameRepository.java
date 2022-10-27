package com.gaff.demo.models;

/*
 * This class interacts with the game database.
 * Last updated 10/25/2022
 * Author(s): Jessica Frank
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository {

    @Autowired
    NamedParameterJdbcTemplate template;

    public List<Game> getAllGames() {
        String query = "SELECT id, name, genre, description, isPC, isConsole, isMobile FROM games";
        return template.query(query,
                (result, rowNum)
                -> new Game(result.getLong("id"), result.getString("name"),
                        result.getString("genre"), result.getString("description"),
                        result.getBoolean("isPC"), result.getBoolean("isPC"),
                        result.getBoolean("isMobile")));
    }

    public Game getGameById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        String query = "SELECT * FROM games WHERE id=:id";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(Game.class));
    }

    public int addNewGame(String name, String genre, String description,
            boolean isPC, boolean isConsole, boolean isMobile) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("genre", genre);
        paramMap.put("description", description);
        paramMap.put("isPC", isPC);
        paramMap.put("isConsole", isConsole);
        paramMap.put("isMobile", isMobile);
        String query = "INSERT INTO games"
                + "(name, genre, description, isPC, isConsole, isMobile) "
                + "VALUES(:name, :genre, :description, :isPC, :isConsole, :isMobile)";
        return template.update(query, paramMap);
    }
    
    public int editGame(long id, String name, String genre, String description,
            boolean isPC, boolean isConsole, boolean isMobile) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("genre", genre);
        paramMap.put("description", description);
        paramMap.put("isPC", isPC);
        paramMap.put("isConsole", isConsole);
        paramMap.put("isMobile", isMobile);
        String query = "UPDATE games SET"
                + "name = :name, genre = :genre, description = :description, "
                + "isPC = :isPC, isConsole = :isConsole, isMobile = :isMobile "
                + "WHERE id = :id";
        return template.update(query, paramMap);
    }
}
