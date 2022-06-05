package ru.kirillisachenko.virusgame.gameobjects.Jam_package;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;

public class Super_Jam_Bullet extends Bullet {
    public Super_Jam_Bullet(float xPosition, float yPosition, float xSpeed, float ySpeed, float bulletSpeed, Context context, double damage, int size) {
        super(xPosition, yPosition, xSpeed, ySpeed, bulletSpeed, context, damage, size);
        Model = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.jam_bullet_1), size, size, false);
    }
}
