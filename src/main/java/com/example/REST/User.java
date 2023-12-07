package com.example.REST;

import java.util.ArrayList;

public class User {
    String username;
    ArrayList<String> comments;

    public User(String name, ArrayList<String> comments) {
        this.username = name;
        this.comments = comments;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

}
