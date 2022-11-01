package com.gaff.demo.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
 * This class represents the GAFF user's information.
 * It is not named User to avoid conflicts with the User class used for login and security
 * Last updated 10/30/2022
 * Author(s): Jessica Frank
 */
@Entity
@Table(name = "users")
public class AppUser implements Serializable {

   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String userRole;
    private String bio;
    private String discordLink;
    private String steamLink;

    public AppUser() {
    }

    public AppUser(long id, String name, String userRole, String bio, String discordLink, String steamLink) {
        this.id = id;
        this.name = name;
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
