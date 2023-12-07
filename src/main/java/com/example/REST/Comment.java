package com.example.REST;

public class Comment {
    String text;
    User user;

    public Comment(String text, User user) {
        this.text = text;
        this.user = user;
    }
}
