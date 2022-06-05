package ru.kirillisachenko.virusgame.gamecontrollers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public class HealthBar {
    private Hero hero;
    private double fullCount, emptyCount;
    private Bitmap healthBar, emptyHealthBar;
    private float xPosition, yPosition, lastXPosition, newLastXPosition;
    private int size;

    public HealthBar(float xPosition, float yPosition, int size, Context context, Hero hero) {
        this.hero = hero;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.size = size;
        healthBar = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.health_bar), size, size, false);
        emptyHealthBar = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_health_bar), size, size, false);
    }

    public void update() {
        fullCount = hero.getHealthPoint();
        emptyCount = hero.getMaxHealthPoint() - hero.getHealthPoint();
    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < fullCount; i++) {
            lastXPosition = xPosition + (i) * size;
            canvas.drawBitmap(healthBar, lastXPosition, yPosition, null);
        }
        if (fullCount != 0) {
            for (int i = 0; i < emptyCount + 1; i++) {
                newLastXPosition = lastXPosition + (i) * size;
                canvas.drawBitmap(emptyHealthBar, newLastXPosition, yPosition, null);
            }
        }
        if (fullCount == 0) {
            for (int i = 0; i < emptyCount; i++) {
                newLastXPosition = lastXPosition + (i) * size;
                canvas.drawBitmap(emptyHealthBar, newLastXPosition, yPosition, null);
            }
        }
    }
}
