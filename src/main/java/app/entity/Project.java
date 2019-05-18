package app.entity;

import java.io.*;


public class Project implements Serializable{

    private String name;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

}
