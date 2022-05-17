package com.valeriu.rickandmortyhope;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CharacterResponse {
    private int id;
    private String name;
    private String status;
    private String species;
    private String image;
    private Location location;
    @SerializedName("episode")
    private ArrayList<String> episode;

    public Character getCharacter() {
        return new Character(id, name, status, species, image, location.name, episode);
    }


    public class Location {

        private String name;
    }











//    public class Episode{
//
//        @SerializedName("episode")
//        private ArrayList<String> episode = new ArrayList<>();
//
//        public ArrayList<String> getEpisode() {
//            return episode;
//        }
//    }


}
