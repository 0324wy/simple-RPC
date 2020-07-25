package com.rockTechnology.entities;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 8005686972860319259L;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
