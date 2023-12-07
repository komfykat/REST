package com.example.REST;

import java.util.ArrayList;

public class User {
    String name;
    int age;
    ArrayList<Comment> comments;

    public User(String name, int age, ArrayList<Comment> comments) {
        this.name = name;
        this.age = age;
        this.comments = comments;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
