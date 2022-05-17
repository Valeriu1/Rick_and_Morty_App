package com.valeriu.rickandmortyhope;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class CharacterRepository {
    private static CharacterRepository instance;
    private final MutableLiveData<Character> searchedCharacter;
    private final MutableLiveData<Episode> characterEpisode;

    public CharacterRepository() {
        this.characterEpisode = new MutableLiveData<>();
        this.searchedCharacter = new MutableLiveData<>();
    }

    public static synchronized CharacterRepository getInstance() {
        if (instance == null) {
            instance = new CharacterRepository();
        }
        return instance;
    }

    public LiveData<Character> getSearchedCharacter() {
        return searchedCharacter;
    }

    public LiveData<Episode> getCharacterEpisode() {
        return characterEpisode;
    }

    public void searchForCharacter(String id) {
        RickandMortyApi rickandMortyApi = ServiceGenerator.getRickandMortyApi();
        Call<CharacterResponse> call = rickandMortyApi.getCharacter(id);
        call.enqueue(new Callback<CharacterResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                if (response.isSuccessful()) {
                    searchedCharacter.setValue(response.body().getCharacter());
                    searchForCharacterEpisode(getIdFromUrl(searchedCharacter.getValue().getEpisodes().get(0)));
                }

            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<CharacterResponse> call, Throwable t) {
                Log.i("Retrofit", t.getMessage());

            }
        });
    }

    public void searchForCharacterEpisode(String id) {
        RickandMortyApi rickandMortyApi = ServiceGenerator.getRickandMortyApi();
        Call<EpisodeResponse> call = rickandMortyApi.getEpisode(id);
        call.enqueue(new Callback<EpisodeResponse>() {
            @Override
            public void onResponse(Call<EpisodeResponse> call, Response<EpisodeResponse> response) {
                if (response.isSuccessful()) {
                    characterEpisode.setValue(response.body().getEpisode());
                }
            }

            @Override
            public void onFailure(Call<EpisodeResponse> call, Throwable t) {
                Log.i("Retrofit", t.getMessage());
            }
        });


//        try {
//           Response<EpisodeResponse> response = call.execute();
//            characterEpisode.setValue(response.body().getEpisode());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public String getIdFromUrl(String url) {

        String result = url.substring(url.indexOf("/episode/") + 9);
        int slash = result.indexOf("/");
        result = slash == -1 ? result
                : result.substring(0, slash);
        System.out.println(result);
        return result;
    }


}
