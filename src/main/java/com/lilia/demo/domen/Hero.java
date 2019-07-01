package com.lilia.demo.domen;

import java.io.Serializable;

public class Hero implements Serializable {
    private int id;
    private String name;

    public Hero(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Hero() {
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
