package com.gaff.demo.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

/*
 * This class represents a game's information
 * Last updated 10/30/2022
 * Author(s): Jessica Frank
 */
@Entity
@Table(name = "games")
@SecondaryTable(name = "connections", pkJoinColumns = @PrimaryKeyJoinColumn(name = "game_id"))
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String genre;
    private String description;

    private boolean isComputer;
    private boolean isConsole;
    private boolean isMobile;

    public Game() {
    }

    public Game(long id, String name, String genre, String description, boolean isComputer, boolean isConsole, boolean isMobile) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.isComputer = isComputer;
        this.isConsole = isConsole;
        this.isMobile = isMobile;
    }

    //================= GETTERS ===============

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

    public boolean getIsComputer() {
        return isComputer;
    }

    public boolean getIsConsole() {
        return isConsole;
    }

    public boolean getIsMobile() {
        return isMobile;
    }


    
    //================= SETTERS ===============

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

    public void setIsComputer(boolean isComputer) {
        this.isComputer = isComputer;
    }

    public void setIsConsole(boolean isConsole) {
        this.isConsole = isConsole;
    }

    public void setIsMobile(boolean isMobile) {
        this.isMobile = isMobile;
    }


    
}
