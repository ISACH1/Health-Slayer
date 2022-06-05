package ru.kirillisachenko.virusgame.gameItems;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public class HealthUp extends Item{


    public HealthUp(float xPosition, float yPosition, Hero hero, Context context) {
        super(xPosition, yPosition, hero, context);
        Model = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.health_up), 70, 90,  false);
    }

    @Override
    public void take() {
        super.take();
        hero.setMaxHealthPoint(hero.getMaxHealthPoint() + 1);
        hero.setHealthPoint(hero.getHealthPoint() + 1);
    }
}
