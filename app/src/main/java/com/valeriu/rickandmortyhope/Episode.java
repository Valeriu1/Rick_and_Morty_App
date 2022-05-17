package com.valeriu.rickandmortyhope;

public class Episode {
    private final int id;
    private final String name;
    private final String url;


    public Episode(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
