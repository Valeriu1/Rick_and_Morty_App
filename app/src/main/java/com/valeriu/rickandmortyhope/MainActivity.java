package com.valeriu.rickandmortyhope;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends Fragment implements View.OnTouchListener {


    private EditText editText;
    private CharacterViewModel viewModel;
    private EpisodeViewModel episodeViewModel;
    private TextView textView;
    private Button button;
    private TextView statusText;
    private TextView speciesText;
    private TextView numberText;
    private TextView lastLocation;
    private CardView cardView;
    private TextView firstIn;
    private View cardViewForInfo;
    private MotionLayout motionLayout;
    private MotionLayout motionLayoutCard;
    private ImageButton imageButton;
    GestureDetector gestureDetector;

    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter = CharacterAdapter.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         gestureDetector=new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {

                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
                navController.navigate(R.id.action_mainActivity2_to_multipleCharacters);
                return true;
            }
        });
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_main, container, false);
        view.setOnTouchListener(this);



        imageButton = view.findViewById(R.id.imageButton);
        button = view.findViewById(R.id.button);
        editText = view.findViewById(R.id.searchName);
        textView = view.findViewById(R.id.name);
        statusText = view.findViewById(R.id.status);
        numberText = view.findViewById(R.id.number);
        cardView = view.findViewById(R.id.cardView);
        speciesText = view.findViewById(R.id.species);
        lastLocation = view.findViewById(R.id.lastLocationData);
        firstIn = view.findViewById(R.id.firstSeenInData);

        cardViewForInfo = view.findViewById(R.id.cardViewForInfoConstraint);
        motionLayout = view.findViewById(R.id.motionLayout);
        motionLayoutCard = view.findViewById(R.id.motionLayoutCard);
        Context context = getContext();




        final MediaPlayer mp =  MediaPlayer.create(getContext(), R.raw.blob);



        viewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        viewModel.getSearchedCharacter().observe(getViewLifecycleOwner(), character -> {
            motionLayout.transitionToEnd();
            motionLayoutCard.transitionToEnd();
            Glide.with(this)
                    .load(character.getImage())
                    .into(imageButton);
            textView.setText(character.getName());
            statusText.setText(character.getStatus());
            speciesText.setText(character.getSpecies());
            numberText.setText(String.valueOf(character.getId()));
            lastLocation.setText(character.getLocation());
            characterAdapter.addCharacterToList(character);



            if (statusText.getText().toString().equalsIgnoreCase("alive")) {
                cardView.setCardBackgroundColor(context.getColor(R.color.green));
                imageButton.setBackground(context.getDrawable(R.drawable.alive));
                cardViewForInfo.setBackground(context.getDrawable(R.drawable.alivegrad));
            } else if (statusText.getText().toString().equalsIgnoreCase("dead")) {
                cardView.setCardBackgroundColor(context.getColor(R.color.red));
                imageButton.setBackground(context.getDrawable(R.drawable.dead));
                cardViewForInfo.setBackground(context.getDrawable(R.drawable.deadgrav));


            } else {
                cardView.setCardBackgroundColor(context.getColor(R.color.unknown));
                imageButton.setBackground(context.getDrawable(R.drawable.unknown));
                cardViewForInfo.setBackground(context.getDrawable(R.drawable.unknowngrad));


            }

        });

        episodeViewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);
        episodeViewModel.getCharacterEpisode().observe(getViewLifecycleOwner(), episode -> {
            firstIn.setText(episode.getName());
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                searchForCharacter();
                editText.setText("");
                mp.start();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewModel.searchForCharacter(String.valueOf(getRandomNumber()));
                editText.setText("");
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.reset();


                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });






        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    searchForCharacter();
                    return true;
                }
                return false;
            }
        });



        // Inflate the layout for this fragment
        return view;
    }

    public void searchForCharacter() {
        if (!editText.getText().toString().isEmpty()) {
            viewModel.searchForCharacter(editText.getText().toString());
        }
        editText.setText("");
    }

    public int getRandomNumber() {
        return (int) ((Math.random() * (826 - 1)) + 1);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        return true;
    }
}