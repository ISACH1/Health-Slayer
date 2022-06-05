package ru.kirillisachenko.virusgame.gameobjects.heropackage.Classic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gamecontrollers.Joystick;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public class ClassicVirus extends Hero {
    public ClassicVirus(Context context, float xPosition, float yPosition, int size, Joystick joystick) {
        super(context,xPosition, yPosition, size, joystick);
        Model[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus1), size, size,false);
        Model[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus2), size,size,false);
        Model[2] = Model[0];
        bulletSpeed = 17f;
        speed = 10f;
        AbilityKD = 20 * 1000;
        attackSpeed = 2000;
        setMaxHealthPoint(5);
        setHealthPoint(5);
        armor = 0;
        damage = 1;
    }

    @Override
    public void castAbility() {
        setHealthPoint(getHealthPoint() + 2);
        lastCast = System.currentTimeMillis();
    }
}
