package ru.kirillisachenko.virusgame.gameobjects.doctor_package;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;

public class DoctorBullet extends Bullet {


    public DoctorBullet(float xPosition, float yPosition, float xSpeed, float ySpeed, float bulletSpeed, Context context, double damage, int size) {
        super(xPosition, yPosition, xSpeed, ySpeed, bulletSpeed, context, damage, size);
        int type = mathGenerator.getRandom(4,0);
        switch (type){
            case (1):
                Model = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.doctor_bullet_1), size, size, false );
                break;
            case (2):
                Model = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.doctor_bullet_2), size, size,false );
                break;
            case (3):
                Model = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.doctor_bullet_3), size, size, false );
                break;
        }
    }
}
