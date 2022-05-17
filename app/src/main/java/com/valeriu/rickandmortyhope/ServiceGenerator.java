package com.valeriu.rickandmortyhope;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static RickandMortyApi rickandMortyApi;

    public static RickandMortyApi getRickandMortyApi() {

        if (rickandMortyApi == null) {
            rickandMortyApi = new Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RickandMortyApi.class);
        }
        return rickandMortyApi;
    }
}
