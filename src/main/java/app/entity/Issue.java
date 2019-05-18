package app.entity;

import java.io.*;

public class Issue implements Serializable{

    private String name;

    public Issue(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

}
