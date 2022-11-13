package com.gaff.demo.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class connects the users to the tables
 * Last updated 11/13/2022
 * Author(s): Jessica Frank, Alec Droegemeier
 */
@Entity
@Table(name = "connections")
public class Connections implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long connection_id;
    private long game_id;
    private long user_id;
    
    public Connections() {
    }

    public Connections(long connectionId, long userId, long gameId) {
        this.connection_id = connectionId;
        this.user_id = userId;
        this.game_id = gameId;
    }

    
    //================= GETTERS ===============
    
    
    public Long getUser_id() {
        return user_id;
    }

    public Long getGame_id() {
        return game_id;
    }

    
    //================= SETTERS ===============
    

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setGame_id(Long game_id) {
        this.game_id = game_id;
    }


    
}
