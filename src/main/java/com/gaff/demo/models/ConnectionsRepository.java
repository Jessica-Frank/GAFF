package com.gaff.demo.models;

/*
 * These are the sql queries to the connections table
 * Last updated 11/14/2022
 * Author(s): Jessica Frank, Alec Droegemeier
 */

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectionsRepository {

    @Autowired
    NamedParameterJdbcTemplate template;

    @Autowired
    UserRepository userRep;

    @Autowired
    GameRepository gameRep;

    public List<User> getUsersByGame(long game_id) {
        SqlParameterSource parameters = new MapSqlParameterSource("game_id", game_id);
        List<Connections> connections = template.query(
                "SELECT * FROM connections WHERE game_id IN (:game_id)",
                parameters,
                (rs, rowNum) -> new Connections(
                        rs.getLong("connection_id"),
                        rs.getLong("user_id"),
                        rs.getLong("game_id")));
        User[] userArray = new User[connections.size()];
        for (int i = 0; i < userArray.length; i++) {
            userArray[i] = userRep.getUserById(connections.get(i).getUserId());
        }
        return Arrays.asList(userArray);
    }

    public List<Game> getGamesByUser(long user_id) {
        SqlParameterSource parameters = new MapSqlParameterSource("user_id", user_id);
        List<Connections> connections = template.query(
                "SELECT * FROM connections WHERE user_id IN (:user_id)",
                parameters,
                (rs, rowNum) -> new Connections(
                        rs.getLong("connection_id"),
                        rs.getLong("user_id"),
                        rs.getLong("game_id")));
        Game[] gameArray = new Game[connections.size()];
        for (int i = 0; i < gameArray.length; i++) {
            gameArray[i] = gameRep.getGameById(connections.get(i).getGameId());
        }
        return Arrays.asList(gameArray);
    }

    public void addNewConnection(Long userId, Long gameId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", userId);
        paramMap.put("game_id", gameId);

        String query = "INSERT INTO connections"
                + "(user_id, game_id) "
                + "VALUES(:user_id, :game_id)";

        template.execute(query, paramMap,
                (PreparedStatement ps) -> ps.executeUpdate());
    }

    public void removeConnection(Long userId, Long gameId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", userId);
        paramMap.put("game_id", gameId);

        String query = "DELETE FROM connections WHERE "
                + "(user_id, game_id)=(:user_id, :game_id)";

        template.execute(query, paramMap,
                (PreparedStatement ps) -> ps.executeUpdate());
    }
}
