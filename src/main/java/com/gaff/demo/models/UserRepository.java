package com.gaff.demo.models;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/*
 * These are sql queries for the user table
 * Last updated 11/10/2022
 * Author(s): Alec Droegemeier
 */
@Repository
public class UserRepository {

    @Autowired
    NamedParameterJdbcTemplate template;

    public AppUser getUserById(long id) {
        SqlParameterSource namedParameters
                = new MapSqlParameterSource().addValue("id", id);
        String query = "SELECT * FROM users WHERE id= :id";
        return template.queryForObject(query, namedParameters,
                BeanPropertyRowMapper.newInstance(AppUser.class));
    }

    public List<AppUser> getAllUsers() {
        String query = "SELECT id, name, user_role, bio, "
                + "discord_link, steam_link FROM users";
        return template.query(query,
                (result, rowNum)
                -> new AppUser(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("user_role"),
                        result.getString("bio"),
                        result.getString("discord_link"),
                        result.getString("steam_link")
                )
        );
    }
}
