package com.valeriu.rickandmortyhope;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class EpisodeViewModel extends ViewModel {
    CharacterRepository repository;

    public EpisodeViewModel() {
        repository = CharacterRepository.getInstance();
    }

    LiveData<Episode> getCharacterEpisode() {
        return repository.getCharacterEpisode();
    }

    public void searchForCharacterEpisode(String id) {
        repository.searchForCharacterEpisode(id);
    }
}
