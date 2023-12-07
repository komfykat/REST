package com.example.REST;

import java.util.ArrayList;

public class Topic {
    String name;
    ArrayList<Comment> comments = new ArrayList<>();

    public Topic(String name, ArrayList<Comment> comments) {
        this.name = name;
        this.comments = comments;
    }

    public Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
