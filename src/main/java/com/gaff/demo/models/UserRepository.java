package com.gaff.demo.models;

/*
 * These are sql queries for the user table
 * Last updated 11/14/2022
 * Author(s): Alec Droegemeier, Jessica Frank
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
public class UserRepository {

    @Autowired
    NamedParameterJdbcTemplate template;

    public User getUserById(long id) {
        SqlParameterSource namedParameters
                = new MapSqlParameterSource().addValue("id", id);
        String query = "SELECT * FROM users WHERE id= :id";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(User.class));
    }

    public User getUserByName(String name) {
        SqlParameterSource namedParameters
                = new MapSqlParameterSource().addValue("name", name);
        String query = "SELECT * FROM users WHERE name= :name";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(User.class));
    }
    
    public void changeUserRole(String name, String newRole) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        paramMap.put("newRole", newRole);
        String query = "UPDATE users SET user_role = :newRole WHERE name= :name";
        template.execute(query, paramMap,
                (PreparedStatement ps) -> ps.executeUpdate());
    }

    public List<User> getAllUsers() {
        String query = "SELECT id, name, password, user_role, bio, "
                + "discord_link, steam_link FROM users";
        return template.query(query,
                (result, rowNum)
                -> new User(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("password"),
                        result.getString("user_role"),
                        result.getString("bio"),
                        result.getString("discord_link"),
                        result.getString("steam_link")
                )
        );
    }
}
