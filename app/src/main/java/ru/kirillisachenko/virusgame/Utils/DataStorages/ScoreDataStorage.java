package ru.kirillisachenko.virusgame.Utils.DataStorages;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.Activities.Adapters_and_Controllers.AuthController;

public class ScoreDataStorage {
    private SharedPreferences storage;
    private AuthController authController;

    public ScoreDataStorage(Context context) {
        storage = context.getSharedPreferences("DEBTS STORAGE", Context.MODE_PRIVATE);
        authController = new AuthController();
    }

    public void saveData(Integer i) {
        int size = storage.getInt("size", 0); // если ничего нет, то вернется 0
        SharedPreferences.Editor editor = storage.edit();
        editor.putInt(authController.getUser().getEmail(), i);
        editor.putInt("size", size + 1);
        editor.apply();
    }

    public Integer getData() {
        return storage.getInt(authController.getUser().getEmail(), -1);
    }
}
