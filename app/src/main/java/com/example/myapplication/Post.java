package com.example.myapplication;

import java.time.LocalDateTime;

public class Post {
    private String date;
    private String title;
    private String body;
    private  String cat;
    private String photos;

    public Post() {
    }

    public Post(String date, String title, String body,
                String cat, String photos) {
        this.date = date;
        this.title = title;
        this.body = body;
        this.cat = cat;
        this.photos = photos;


    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
