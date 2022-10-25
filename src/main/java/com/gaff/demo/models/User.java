package com.gaff.demo.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
 * This class represents the GAFF user's information
 * Last updated 10/25/2022
 * Author(s): Jessica Frank
 */
@Entity
@Table(name="users")
public class User implements Serializable {
    @Transient
    public static String ROLE_ADMIN = "ADM";
    @Transient
    public static String ROLE_MODERATOR = "MOD";
    @Transient
    public static String ROLE_PLAYER = "PLY";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String name;
    private String userRole;
    private String bio;
    private String discordLink;
    private String steamLink;

    public User() {
    }

    public User(String name, String userRole, String bio, String discordLink, String steamLink) {
        this.name = name;
        this.userRole = userRole;
        this.bio = bio;
        this.discordLink = discordLink;
        this.steamLink = steamLink;
    }
    
    public User(int id, String name, String userRole, String bio, String discordLink, String steamLink) {
        this.id = id;
        this.name = name;
        this.userRole = userRole;
        this.bio = bio;
        this.discordLink = discordLink;
        this.steamLink = steamLink;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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