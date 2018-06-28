package com.example.bhart.savewater;

public class User {
    public String email;
    public String topic;
    public String detail;
    public int likes;
    public int dislikes;

    public String getEmail() {
        return email;
    }

    public String getTopic() {
        return topic;
    }

    public String getDetail() {
        return detail;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public User() {
        //Default constructor
    }

    public User(String email, String topic, String detail, int likes, int dislikes) {
        this.email = email;
        this.topic = topic;
        this.detail = detail;
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
