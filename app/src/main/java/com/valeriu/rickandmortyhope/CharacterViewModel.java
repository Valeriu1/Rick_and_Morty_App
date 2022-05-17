package com.valeriu.rickandmortyhope;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CharacterViewModel extends ViewModel {
    CharacterRepository repository;

    public CharacterViewModel() {
        repository = CharacterRepository.getInstance();
    }

    LiveData<Character> getSearchedCharacter() {
        return repository.getSearchedCharacter();
    }

    public void searchForCharacter(String id){
        repository.searchForCharacter(id);
    }




}
