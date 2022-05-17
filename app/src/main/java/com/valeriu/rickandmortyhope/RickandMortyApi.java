package com.valeriu.rickandmortyhope;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RickandMortyApi {
    @GET("character/{id}")
    Call<CharacterResponse> getCharacter(@Path("id") String id);

    @GET("episode/{id}")
    Call<EpisodeResponse> getEpisode(@Path("id") String id);


}
