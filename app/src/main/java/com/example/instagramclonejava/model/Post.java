package com.example.instagramclonejava.model;

public class Post {

    private String email;
    private String comment;
    private String dowloadUrl;

    public Post() {
    }

    public Post(String email, String comment, String dowloadUrl) {
        this.email = email;
        this.comment = comment;
        this.dowloadUrl = dowloadUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDowloadUrl() {
        return dowloadUrl;
    }

    public void setDowloadUrl(String dowloadUrl) {
        this.dowloadUrl = dowloadUrl;
    }
}
