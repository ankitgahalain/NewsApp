package com.example.newsapp.model;

public class NewsItem {
    private String title, imageUrl, description;

    public NewsItem(String title, String imageUrl, String description) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    // Getters
    public String getTitle() { return title; }
    public String getImageUrl() { return imageUrl; }
    public String getDescription() { return description; }
}