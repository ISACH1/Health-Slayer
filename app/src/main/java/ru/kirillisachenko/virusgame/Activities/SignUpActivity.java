package ru.kirillisachenko.virusgame.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import ru.kirillisachenko.virusgame.Activities.Adapters_and_Controllers.AuthController;
import ru.kirillisachenko.virusgame.databinding.ActivitySignInBinding;
import ru.kirillisachenko.virusgame.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.alreadySignedUp.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            finish();
        });
        AuthController controller = new AuthController();
        binding.register.setOnClickListener(view -> {
            String email = binding.email.getText().toString();
            String password = binding.password.getText().toString();
            String confirmPassword = binding.confirmPassword.getText().toString();
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Необходимо заполнить все поля!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Пароли должны совпадать!", Toast.LENGTH_SHORT).show();
                return;
            }
            controller.registerNewUser(email, password, task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, task.getResult().getUser().getEmail(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    finish();
                } else {
                    String message = task.getException().getMessage();
                    if(message.startsWith("The email address is badly")){
                        Toast.makeText(this, "Неверно введена почта!", Toast.LENGTH_SHORT).show();
                    }
                    if(message.startsWith("The given password is invalid")){
                        Toast.makeText(this, "Пароль должен содержать минимум 6 символов!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}