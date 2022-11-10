package com.gaff.demo.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class connects the users to the tables
 * Last updated 11/10/2022
 * Author(s): Alec Droegemeier
 */
@Entity
@Table(name = "connections")
public class Connections implements Serializable {
    
    @Id
    private Long game_id;
    private Long user_id;
    
    public Connections() {
    }

    public Connections(Long user_id, Long game_id) {

        this.user_id = user_id;
        this.game_id = game_id;
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
