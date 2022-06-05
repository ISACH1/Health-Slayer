package ru.kirillisachenko.virusgame.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import ru.kirillisachenko.virusgame.Game;
import ru.kirillisachenko.virusgame.Utils.DataStorages.RealTimeDataBase;
import ru.kirillisachenko.virusgame.Utils.DataStorages.ScoreDataStorage;
import ru.kirillisachenko.virusgame.databinding.ActivityLoseBinding;

public class LoseActivity extends AppCompatActivity {
    ActivityLoseBinding binding;
    ScoreDataStorage scoreDataStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toast.makeText(this, "Подождите идёт загрузка данных с сервера", Toast.LENGTH_SHORT).show();
        scoreDataStorage = new ScoreDataStorage(getApplicationContext());
        RealTimeDataBase r = new RealTimeDataBase();
        Integer lastScore = getIntent().getIntExtra("SCORE", 0);
        final Integer[] HighestScore = {scoreDataStorage.getData()};
        if(HighestScore[0] < lastScore) HighestScore[0] = lastScore;
        binding.highestScore.setText(String.valueOf(HighestScore[0]));
        binding.score.setText(String.valueOf(lastScore));
        Integer finalHighestScore = HighestScore[0];
        r.getScore(task -> {
            Integer i;
            if (task.getResult().getValue(Integer.class) == null) {
                i = -1;
                if (i > finalHighestScore){
                    HighestScore[0] = i;
                    r.writeScore(HighestScore[0]);
                    binding.highestScore.setText(String.valueOf(i));
                }
                if (i < HighestScore[0]) r.writeScore(HighestScore[0]);
            }
            if(task.getResult().getValue(Integer.class) != null) {
                i = task.getResult().getValue(Integer.class);
                if (i > finalHighestScore){
                    HighestScore[0] = i;
                    r.writeScore(HighestScore[0]);
                    binding.highestScore.setText(String.valueOf(i));
                }
                if (i < HighestScore[0]) r.writeScore(HighestScore[0]);
            }
            Toast.makeText(this, "Загрузка завершена", Toast.LENGTH_SHORT).show();
            scoreDataStorage.saveData(HighestScore[0]);
        });
        setButtons();
    }

    public void setButtons(){
        binding.restart.setOnClickListener(v -> {
            startActivity(new Intent(this, ChooseHeroActivity.class));
            finish();
        });
        binding.maimMenu.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}