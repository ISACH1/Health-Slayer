package ru.kirillisachenko.virusgame.Activities.Adapters_and_Controllers;

import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.R;

public class CarouselRecyclerAdapter extends RecyclerView.Adapter<CarouselRecyclerAdapter.MyHolder> {
    ArrayList<Integer> heroPNG;

    public CarouselRecyclerAdapter() {
        heroPNG = new ArrayList<>();
        heroPNG.add(R.drawable.virus_state);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView hero;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            hero = itemView.findViewById(R.id.hero);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.carousel_hero, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.hero.setBackgroundResource(heroPNG.get(position));
        AnimationDrawable animationDrawable = (AnimationDrawable) holder.hero.getBackground();
        animationDrawable.start();
    }

    @Override
    public int getItemCount() {
        return heroPNG.size();
    }
}
