package ru.kirillisachenko.virusgame.gameobjects.heropackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Classic.ClassicVirus;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Ninja.NinjaVirus;

public class HeroBullet extends Bullet {

    public HeroBullet(float xPosition, float yPosition, float xSpeed, float ySpeed, float bulletSpeed, Context context, double damage, Hero hero, int size) {
        super(xPosition, yPosition, xSpeed, ySpeed, bulletSpeed, context, damage, size);
        if(hero.getClass() == ClassicVirus.class) Model = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.hero_classic_bullet ), size, size, false);
        if(hero.getClass() == NinjaVirus.class) Model = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_bullet ), size, size, false);
    }
}
