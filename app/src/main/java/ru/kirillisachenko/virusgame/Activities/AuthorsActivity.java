package ru.kirillisachenko.virusgame.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import ru.kirillisachenko.virusgame.databinding.ActivityAuthorsBinding;

public class AuthorsActivity extends AppCompatActivity {
    ActivityAuthorsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthorsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AnimationDrawable animationDrawable = (AnimationDrawable) binding.back.getBackground();
        animationDrawable.start();
        binding.exit.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}