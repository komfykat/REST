package com.example.REST;

public class Comment {
    String text;
    String username;

    public Comment(String text, String username) {
        this.text = text;
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
