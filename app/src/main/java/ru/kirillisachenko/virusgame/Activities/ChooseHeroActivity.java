package ru.kirillisachenko.virusgame.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gtomato.android.ui.transformer.FlatMerryGoRoundTransformer;

import ru.kirillisachenko.virusgame.Activities.Adapters_and_Controllers.CarouselRecyclerAdapter;
import ru.kirillisachenko.virusgame.Game;
import ru.kirillisachenko.virusgame.databinding.ActivityChooseHeroBinding;

public class ChooseHeroActivity extends AppCompatActivity {
    ActivityChooseHeroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseHeroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.carousel.setTransformer(new FlatMerryGoRoundTransformer());
        binding.carousel.setAdapter(new CarouselRecyclerAdapter());
        binding.carousel.setOnItemClickListener((adapter, view, i, i1) -> {
            setContentView(new Game(this, i));
        });
        binding.exit.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}