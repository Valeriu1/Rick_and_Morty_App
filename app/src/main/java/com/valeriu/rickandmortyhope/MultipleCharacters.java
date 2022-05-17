package com.valeriu.rickandmortyhope;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MultipleCharacters extends Fragment {

    private CharacterViewModel viewModel;
    private EpisodeViewModel episodeViewModel;
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter = CharacterAdapter.getInstance();

    private View view;








    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);









    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_multiple_characters, container, false);


        viewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        episodeViewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(gridLayoutManager);

        characterAdapter.setContext(getContext());
//        viewModel.getSearchedCharacter().observe(getViewLifecycleOwner(),character -> {
//
//
//            characterAdapter.addCharacterToList(character);
//        });

        recyclerView.setAdapter(characterAdapter);
        return view;
    }
}