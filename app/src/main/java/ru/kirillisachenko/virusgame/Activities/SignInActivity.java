package ru.kirillisachenko.virusgame.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import ru.kirillisachenko.virusgame.Activities.Adapters_and_Controllers.AuthController;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.Theme_VirusGame);
        AuthController authController = new AuthController();
        if (authController.isRegistered()) {
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
            finish();
        }
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.enter.setOnClickListener(view -> {
            String email = binding.email.getText().toString();
            String password = binding.password.getText().toString();
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Необходимо заполнить все поля!", Toast.LENGTH_SHORT).show();
                return;
            }
            authController.signIn(email, password, task -> {
                if (task.isSuccessful()) {
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    finish();
                } else {
                    String message = task.getException().getMessage();
                    if(message.startsWith("The email address is badly")){
                        Toast.makeText(this, "Неверно введена почта!", Toast.LENGTH_SHORT).show();
                    }
                    if (message.startsWith("There is no user")){
                        Toast.makeText(this, "Такого аккаунта не существует!", Toast.LENGTH_SHORT).show();
                    }
                    if(message.startsWith("The password is invalid")){
                        Toast.makeText(this, "Неверный пароль!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
        binding.notSignedUp.setOnClickListener(view -> {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            finish();
        });
    }
}