package com.valeriu.rickandmortyhope;

import java.util.ArrayList;

public class Character {
    private final int id;
    private final String name;
    private final String status;
    private final String species;
    private final String image;
    private final String location;
    private final ArrayList<String> episodes;


    public Character(int id, String name, String status, String species, String image, String location, ArrayList<String> episodes) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.image = image;
        this.location = location;
        this.episodes = episodes;
    }
    public Character( String name,String image) {
        this.id = 1;
        this.name = name;
        this.status = "";
        this.species = "";
        this.image = image;
        this.location = "";
        this.episodes = null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getImage() {
        return image;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<String> getEpisodes() {
        return episodes;
    }
}
