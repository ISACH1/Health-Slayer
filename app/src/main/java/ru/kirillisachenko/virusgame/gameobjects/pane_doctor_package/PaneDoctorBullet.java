package ru.kirillisachenko.virusgame.gameobjects.pane_doctor_package;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;

public class PaneDoctorBullet extends Bullet {

    public PaneDoctorBullet(float xPosition, float yPosition, float xSpeed, float ySpeed, float bulletSpeed, Context context, double damage, int size) {
        super(xPosition, yPosition, xSpeed, ySpeed, bulletSpeed, context, damage, size);
        Model = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pane_doctor_bullet), size, size, false);
    }
}
