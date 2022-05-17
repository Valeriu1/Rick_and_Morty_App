package com.valeriu.rickandmortyhope;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private ArrayList<Character> characters;
    private OnClickListener listener;
    private Context context;
    private MotionLayout motionLayout;
    private static CharacterAdapter instance;
    public static int mExpandedPosition = -1;
    public static int previousExpandedPosition = -1;


    public CharacterAdapter() {
        this.context = null;
        characters = new ArrayList<>();

    }


    public void setContext(Context context) {
        this.context = context;
    }

    public static synchronized CharacterAdapter getInstance() {
        if (instance == null) {
            instance = new CharacterAdapter();
        }
        return instance;
    }

    public void addCharacterToList(Character character) {

        if (!characters.contains(character)) {
            characters.add(character);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_multiple_characters, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        final boolean isExpanded = position ==mExpandedPosition;
//        holder.cardView.setVisibility(isExpanded?View.VISIBLE:View.GONE);
//        holder.itemView.setActivated(isExpanded);
//
//        if (isExpanded)
//            previousExpandedPosition = holder.getAdapterPosition();
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mExpandedPosition = isExpanded ? -1:holder.getAdapterPosition();
//                notifyItemChanged(previousExpandedPosition);
//                notifyItemChanged(holder.getAdapterPosition());
//            }
//        });

//        final boolean isExpanded = position==mExpandedPosition;
//        holder.cardView.setVisibility(isExpanded?View.VISIBLE:View.GONE);
//        holder.itemView.setActivated(isExpanded);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mExpandedPosition = isExpanded ? -1:holder.getAdapterPosition();
//
//                TransitionManager.beginDelayedTransition(recyclerView);
//                notifyItemChanged(holder.getAdapterPosition());
//            }
//        });

        holder.name.setText(characters.get(position).getName());
        holder.species.setText(characters.get(position).getSpecies());
        holder.id.setText(String.valueOf(characters.get(position).getId()));
        Glide.with(context).load(characters.get(position).getImage()).into(holder.imageButton);
        if (characters.get(position).getStatus().equalsIgnoreCase("alive")) {
            holder.imageButton.setBackground(context.getDrawable(R.drawable.alivemultchar));
            holder.constraintLayout.setBackgroundColor(context.getColor(R.color.green));
        } else if (characters.get(position).getStatus().equalsIgnoreCase("dead")) {
            holder.imageButton.setBackground(context.getDrawable(R.drawable.deadmultchar));
            holder.constraintLayout.setBackgroundColor(context.getColor(R.color.red));
        } else {
            holder.imageButton.setBackground(context.getDrawable(R.drawable.unknownmultchar));
            holder.constraintLayout.setBackgroundColor(context.getColor(R.color.unknown));

        }

        final boolean isExpanded = position == mExpandedPosition;
        holder.constraintLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mExpandedPosition = isExpanded ? -1 : holder.getAdapterPosition();
                notifyItemChanged(holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return characters.size();
    }


    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView id;
        private final TextView species;
        private final ImageButton imageButton;
        private final ConstraintLayout constraintLayout;




        ViewHolder(View itemview) {
            super(itemview);

            name = itemview.findViewById(R.id.multipleCharactersCardViewHolderTextView);
            imageButton = itemview.findViewById(R.id.multipleCharactersImageButton);
            id = itemview.findViewById(R.id.numberTheId);
            species = itemview.findViewById(R.id.speciesMultChar);
            constraintLayout = itemview.findViewById(R.id.linearLayoutMultChar);


//            itemview.setOnClickListener(view -> {
//            });

        }
    }

    public interface OnClickListener {
        void onClick(Character character);
    }


}