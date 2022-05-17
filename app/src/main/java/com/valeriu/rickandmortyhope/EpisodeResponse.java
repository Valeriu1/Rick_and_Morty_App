package com.valeriu.rickandmortyhope;

public class EpisodeResponse {
    private int id;
    private String name;
    private String url;

    public Episode getEpisode() {
        return new Episode(id, name, url);
    }
}

