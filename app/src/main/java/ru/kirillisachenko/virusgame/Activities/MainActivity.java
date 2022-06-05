package ru.kirillisachenko.virusgame.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ru.kirillisachenko.virusgame.Activities.Adapters_and_Controllers.AuthController;
import ru.kirillisachenko.virusgame.Game;
import ru.kirillisachenko.virusgame.Utils.DataStorages.RealTimeDataBase;
import ru.kirillisachenko.virusgame.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    AuthController authController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        authController = new AuthController();
        setButtons();
    }

    public void setButtons() {
        binding.logout.setOnClickListener(view -> {
            authController.logOut();
            startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            finish();
        });
        binding.start.setOnClickListener(v -> {
            startActivity(new Intent(this, ChooseHeroActivity.class));
            finish();
        });
        binding.authors.setOnClickListener(v -> {
            startActivity(new Intent(this, AuthorsActivity.class));
            finish();
        });
        binding.exit.setOnClickListener(view -> {
            System.exit(0);
        });
        binding.userEmail.setText(authController.getUser().getEmail());
    }
}