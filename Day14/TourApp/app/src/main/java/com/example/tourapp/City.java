package com.example.tourapp;

public class City {
    int imageID;
    String name;
    boolean isFavorite;

    public City(int imageID, String name, boolean isFavorite) {
        this.imageID = imageID;
        this.name = name;
        this.isFavorite = isFavorite;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
