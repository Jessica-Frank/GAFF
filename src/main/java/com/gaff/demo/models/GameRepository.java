package com.gaff.demo.models;

/*
 * This class interacts with the game database.
 * Last updated 10/28/2022
 * Author(s): Jessica Frank
 */
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

@Repository
public class GameRepository {

    @Autowired
    NamedParameterJdbcTemplate template;

    public List<Game> getAllGames() {
        String query = "SELECT id, name, genre, description, "
                + "is_computer, is_console, is_mobile FROM games";
        return template.query(query,
                (result, rowNum)
                -> new Game(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("genre"),
                        result.getString("description"),
                        result.getBoolean("is_computer"),
                        result.getBoolean("is_console"),
                        result.getBoolean("is_mobile")
                )
        );
    }
    
    public List<Game> getActionGames() {
        String query = "SELECT id, name, genre, description, "
                + "is_computer, is_console, is_mobile FROM games Where genre='Action'";
        return template.query(query,
                (result, rowNum)
                -> new Game(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("genre"),
                        result.getString("description"),
                        result.getBoolean("is_computer"),
                        result.getBoolean("is_console"),
                        result.getBoolean("is_mobile")
                )
        );
    }
    
    public List<Game> getAdventureGames() {
        String query = "SELECT id, name, genre, description, "
                + "is_computer, is_console, is_mobile FROM games Where genre='Adventure'";
        return template.query(query,
                (result, rowNum)
                -> new Game(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("genre"),
                        result.getString("description"),
                        result.getBoolean("is_computer"),
                        result.getBoolean("is_console"),
                        result.getBoolean("is_mobile")
                )
        );
    }
    public List<Game> getSimulationGames() {
        String query = "SELECT id, name, genre, description, "
                + "is_computer, is_console, is_mobile FROM games Where genre='Simulation'";
        return template.query(query,
                (result, rowNum)
                -> new Game(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("genre"),
                        result.getString("description"),
                        result.getBoolean("is_computer"),
                        result.getBoolean("is_console"),
                        result.getBoolean("is_mobile")
                )
        );
    }

    public List<Game> getPuzzleGames() {
        String query = "SELECT id, name, genre, description, "
                + "is_computer, is_console, is_mobile FROM games Where genre='Puzzle'";
        return template.query(query,
                (result, rowNum)
                -> new Game(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("genre"),
                        result.getString("description"),
                        result.getBoolean("is_computer"),
                        result.getBoolean("is_console"),
                        result.getBoolean("is_mobile")
                )
        );
    }
    
    public List<Game> getRacingGames() {
        String query = "SELECT id, name, genre, description, "
                + "is_computer, is_console, is_mobile FROM games Where genre='Racing'";
        return template.query(query,
                (result, rowNum)
                -> new Game(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("genre"),
                        result.getString("description"),
                        result.getBoolean("is_computer"),
                        result.getBoolean("is_console"),
                        result.getBoolean("is_mobile")
                )
        );
    }
    
    public List<Game> getSportsGames() {
        String query = "SELECT id, name, genre, description, "
                + "is_computer, is_console, is_mobile FROM games Where genre='Sports'";
        return template.query(query,
                (result, rowNum)
                -> new Game(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("genre"),
                        result.getString("description"),
                        result.getBoolean("is_computer"),
                        result.getBoolean("is_console"),
                        result.getBoolean("is_mobile")
                )
        );
    }

    public Game getGameById(long id) {
        SqlParameterSource namedParameters
                = new MapSqlParameterSource().addValue("id", id);
        String query = "SELECT * FROM games WHERE id=:id";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(Game.class));
    }

    public void addNewGame(String name, String genre, String description,
            boolean isComputer, boolean isConsole, boolean isMobile) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("genre", genre);
        paramMap.put("description", description);
        paramMap.put("isComputer", isComputer);
        paramMap.put("isConsole", isConsole);
        paramMap.put("isMobile", isMobile);

        String query = "INSERT INTO games"
                + "(name, genre, description, "
                + "is_computer, is_console, is_mobile) "
                + "VALUES(:name, :genre, :description, "
                + ":isComputer, :isConsole, :isMobile)";

        template.execute(query, paramMap,
                (PreparedStatement ps) -> ps.executeUpdate());
    }

    public void editGame(long id, String name, String genre, String description,
            boolean isComputer, boolean isConsole, boolean isMobile) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("genre", genre);
        paramMap.put("description", description);
        paramMap.put("isComputer", isComputer);
        paramMap.put("isConsole", isConsole);
        paramMap.put("isMobile", isMobile);

        String query = "UPDATE games SET"
                + "name = :name, genre = :genre, description = :description, "
                + "is_computer = :isComputer, is_console = :isConsole, "
                + "is_mobile = :isMobile WHERE id = :id";

        template.execute(query, paramMap,
                (PreparedStatement ps) -> ps.executeUpdate());
    }
}
