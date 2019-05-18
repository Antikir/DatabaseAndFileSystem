package app.entity;

import java.io.*;


public class User implements Serializable{

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

}
