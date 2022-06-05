package ru.kirillisachenko.virusgame.gameobjects.doctor_package;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameItems.Item;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;
import ru.kirillisachenko.virusgame.gameobjects.Enemy;
import ru.kirillisachenko.virusgame.gameobjects.GameObject;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;
import ru.kirillisachenko.virusgame.gameobjects.pane_doctor_package.PaneDoctorBullet;


public class Doctor extends Enemy {
    protected float bulletSpeed = 10f;

    public Doctor(float xPosition, float yPosition, int size, Context context, Hero hero, boolean dropItem, float attackRange) {
        super(xPosition, yPosition, size, context, hero, dropItem, attackRange);
        Model[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.doctor_1), size, size, false);
        Model[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.doctor_2), size, size, false);
        Model[2] = Model[0];
        healthPoint = maxHealthPoint = 4;
        this.speed = 3f;
        this.attackSpeed = 4000;
        minDistance = 400;
        damage = 1;
        score = 10;
        this.attackRange = 1000;
    }


    @Override
    public Bullet attack(Context context) {
        float bulletXSpeed = getBulletXSpeed();
        float bulletYSpeed = getBulletYSpeed();
        lastAttack = System.currentTimeMillis();
        return new DoctorBullet(xPosition, yPosition, bulletXSpeed, bulletYSpeed, bulletSpeed, context, damage, size / 3);
    }

}
