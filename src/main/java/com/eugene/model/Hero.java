package com.eugene.model;

/**
 * Created by DCLab on 2016/11/10.
 */
public class Hero{
    int id;
    String name;

    public Hero() {
    }

    public Hero(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
