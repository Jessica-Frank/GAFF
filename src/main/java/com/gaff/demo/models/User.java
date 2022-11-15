package com.gaff.demo.models;

/*
 * This class represents the GAFF user's information.
 * Last updated 11/14/2022
 * Author(s): Jessica Frank
 */

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "users")
public class User implements Serializable {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String name;
    private String password;
    private String userRole;
    private String bio;
    private String discordLink;
    private String steamLink;
    
 
    
    public User() {
    }

    public User(long id, String name, String password, String userRole, String bio, String discordLink, String steamLink) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userRole = userRole;
        this.bio = bio;
        this.discordLink = discordLink;
        this.steamLink = steamLink;
    }
    
    public User(String name, String password, String userRole, String bio, String discordLink, String steamLink) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userRole = userRole;
        this.bio = bio;
        this.discordLink = discordLink;
        this.steamLink = steamLink;
    }

    //================= GETTERS ===============
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getBio() {
        return bio;
    }

    public String getDiscordLink() {
        return discordLink;
    }

    public String getSteamLink() {
        return steamLink;
    }

    //================= SETTERS ===============
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setDiscordLink(String discordLink) {
        this.discordLink = discordLink;
    }

    public void setSteamLink(String steamLink) {
        this.steamLink = steamLink;
    }

}
