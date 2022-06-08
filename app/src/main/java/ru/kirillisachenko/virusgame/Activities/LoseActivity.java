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
    RealTimeDataBase realTimeDataBase;
    Integer lastScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        scoreDataStorage = new ScoreDataStorage(getApplicationContext());
        realTimeDataBase = new RealTimeDataBase();
        Integer HighestScore = scoreDataStorage.getData();
        lastScore = getIntent().getIntExtra("SCORE", 0);
        if (HighestScore == null) HighestScore = -1;
        if (lastScore > HighestScore) scoreDataStorage.saveData(lastScore);
        binding.highestScore.setText(String.valueOf(scoreDataStorage.getData()));
        binding.score.setText(String.valueOf(lastScore));
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
        binding.download.setOnClickListener(v -> {
            final Integer[] HighestScore = {lastScore};
            Integer finalHighestScore = HighestScore[0];
            realTimeDataBase.getScore(task -> {
                Integer i;
                if (task.getResult().getValue(Integer.class) == null) {
                    realTimeDataBase.writeScore(HighestScore[0]);
                }
                if(task.getResult().getValue(Integer.class) != null) {
                    i = task.getResult().getValue(Integer.class);
                    if (i > finalHighestScore){
                        Toast.makeText(this, "Вам не удалось побить свой рекорд :(, текущий рекорд: " + i, Toast.LENGTH_SHORT).show();
                        HighestScore[0] = i;
                        realTimeDataBase.writeScore(HighestScore[0]);
                        binding.highestScore.setText(String.valueOf(i));
                    }
                    if (i < HighestScore[0]) {
                        realTimeDataBase.writeScore(HighestScore[0]);
                        Toast.makeText(this, "Вы побили свой рекорд! Прошлый рекорд: " + i, Toast.LENGTH_SHORT).show();
                    }
                }
                scoreDataStorage.saveData(HighestScore[0]); // TODO СОХРАНЯТЬ НА ТЕЛЕФОНЕ ТО ЧТО НА СЕРВЕРЕ ИЛИ НЕТ?
            });
        });
    }
}