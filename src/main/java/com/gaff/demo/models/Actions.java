package com.gaff.demo.models;

/*
 * This class represents the actions.
 * Last updated 11/14/2022
 * Author(s): Alex Wesley
 */

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actions")
public class Actions implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String activity;
    private String userName;
    
    private Date day;

    public Actions() {
    }

    public Actions(Long id, String userName, String activity, Date day) {
        this.id = id;
        this.activity = activity;
        this.userName = userName;
        this.day = day;
    }

    
    //================= GETTERS ===============
    
    public Long getId() {
        return id;
    }

    public String getActivity() {
        return activity;
    }

    public String getUserName() {
        return userName;
    }

    public Date getDay() {
        return day;
    }

    //================= SETTERS ===============
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDay(Date day) {
        this.day = day;
    }
    
}
