package com.gaff.demo.models;

/*
 * This class connects the users to the tables
 * Last updated 11/13/2022
 * Author(s): Jessica Frank, Alec Droegemeier
 */

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "connections")
public class Connections implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="connection_id")
    private long connectionId;
    
    @Column(name="game_id", nullable=false)
    private long gameId; 
    
    @Column(name="user_id", nullable=false)
    private long userId;
    
    public Connections() {
    }

    public Connections(long connectionId, long userId, long gameId) {
        this.connectionId = connectionId;
        this.userId = userId;
        this.gameId = gameId;
    }

    
    //================= GETTERS ===============
    
    
    public long getConnectionId() {
        return connectionId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getGameId() {
        return gameId;
    }

    
    //================= SETTERS ===============
    

    public void setConnectionId(long connectionId) {
        this.connectionId = connectionId;
    }

    public void setUserId(Long user_id) {
        this.userId = user_id;
    }

    public void setGameId(Long game_id) {
        this.gameId = game_id;
    }
  
}