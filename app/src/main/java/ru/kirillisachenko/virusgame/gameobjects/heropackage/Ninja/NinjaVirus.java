package ru.kirillisachenko.virusgame.gameobjects.heropackage.Ninja;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gamecontrollers.Joystick;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public class NinjaVirus extends Hero {
    public NinjaVirus(Context context, float xPosition, float yPosition, int size, Joystick joystick) {
        super(context, xPosition, yPosition, size, joystick);
        Model[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_2), size, size, false);
        Model[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_1), size, size, false);
        Model[2] = Model[0];
        bulletSpeed = 17f;
        speed = 10f;
        AbilityKD = 30 * 1000;
        attackSpeed = 2000;
        setMaxHealthPoint(4);
        setHealthPoint(4);
        armor = 0;
        damage = 1.5;
    }

    @Override
    public void castAbility() {
        setDamage(getDamage() + 0.25);
        lastCast = System.currentTimeMillis();
    }
}
