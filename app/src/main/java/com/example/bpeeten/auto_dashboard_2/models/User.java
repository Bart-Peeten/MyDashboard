package com.example.bpeeten.auto_dashboard_2.models;

import java.io.Serializable;

/**
 * Created by bpeeten on 11/02/18.
 */

public class User implements Serializable {
    private long  userId;
    private String name;
    private String email;
    private String passwd;

    public User() {
    }

    public User(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
    }

    public User(String name, String email, String passwd) {
        this.name = name;
        this.email = email;
        this.passwd = passwd;
    }

    public User(int userId, String name, String email, String passwd) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.passwd = passwd;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}


