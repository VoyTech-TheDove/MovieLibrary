package com.company;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String login;
    private String password;

    private static int nextID = 0;

    public User(String login, String password) {
        this.id = nextID++;
        this.login = login;
        this.password = password;
    }

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        if (id >= nextID) {
            nextID += id;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
