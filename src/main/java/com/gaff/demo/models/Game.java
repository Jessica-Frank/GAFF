package com.gaff.demo.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This class represents the GAFF user's information
 * Last updated 10/25/2022
 * Author(s): Jessica Frank
 */
@Entity
@Table(name="games")
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String name;
    private String genre;
    private String description;
    
    private boolean isPC;
    private boolean isConsole;
    private boolean isMobile;

    public Game() {
    }

    public Game(String name, String genre, String description, boolean isPC, boolean isConsole, boolean isMobile) {
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.isPC = isPC;
        this.isConsole = isConsole;
        this.isMobile = isMobile;
    }

    public Game(long id, String name, String genre, String description, boolean isPC, boolean isConsole, boolean isMobile) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.isPC = isPC;
        this.isConsole = isConsole;
        this.isMobile = isMobile;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public boolean isIsPC() {
        return isPC;
    }

    public boolean isIsConsole() {
        return isConsole;
    }

    public boolean isIsMobile() {
        return isMobile;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsPC(boolean isPC) {
        this.isPC = isPC;
    }

    public void setIsConsole(boolean isConsole) {
        this.isConsole = isConsole;
    }

    public void setIsMobile(boolean isMobile) {
        this.isMobile = isMobile;
    }
    
    
}
