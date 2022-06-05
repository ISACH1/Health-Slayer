package ru.kirillisachenko.virusgame.gameobjects.Jam_package;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.Boss;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public class Jam extends Boss {
    protected float bulletSpeed = 24f;

    public Jam(float xPosition, float yPosition, int size, Context context, Hero hero, ArrayList<Bullet> bullets, boolean dropItem, float attackRange) {
        super(xPosition, yPosition, size, context, hero, bullets, dropItem, attackRange);
        Model[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.jam_1), size, size, false);
        Model[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.jam_2), size, size, false);
        Model[2] = Model[0];
        xSpeed = ySpeed = speed = 0;
        attackSpeed = 3000;
        healthPoint = maxHealthPoint = 20;
        damage = 1;
        score = 20;
        lastAttack = 0;
    }


    @Override
    public void Attack() {
        int type = mathGenerator.getRandom(3, 0);
        Log.d("RRR", String.valueOf(type));
        switch (type) {
            case (1):
                attack1();
                break;
            case (2):
                attack2();
                break;
        }
    }

    private void attack1() {
        ArrayList<Bullet> bullets1 = new ArrayList<>();
        float bulletXSpeed = getBulletXSpeed();
        float bulletYSpeed = getBulletYSpeed();
        for (int i = 0; i < 5; i++) {
            bullets1.add(new Jam_bullet(xPosition + mathGenerator.getRandom(size / 7, -size / 7), yPosition + mathGenerator.getRandom(size / 7, -size / 7), bulletXSpeed, bulletYSpeed, bulletSpeed, context, 1, size / 8));
        }
        bullets.addAll(bullets1);
        lastAttack = System.currentTimeMillis();
    }

    private void attack2() {
        new attack2Thread().start();
        lastAttack = System.currentTimeMillis();
    }

    @Override
    public void update() {
        if (hero.getxPosition() > xPosition) xSpeed = 1;
        if (hero.getxPosition() < xPosition) xSpeed = -1;
        if (hero.getxPosition() == xPosition) xSpeed = 0;
    }

    private class attack2Thread extends Thread {
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                try {
                    bullets.add(new Jam_bullet(xPosition, yPosition, getBulletXSpeed(), getBulletYSpeed(), bulletSpeed, context, 1, size / 8));
                    sleep(1000);
                    bullets.add(new Jam_bullet(xPosition, yPosition, getBulletXSpeed(), getBulletYSpeed(), bulletSpeed, context, 1, size / 8));
                    sleep(1000);
                    bullets.add(new Super_Jam_Bullet(xPosition, yPosition, getBulletXSpeed(), getBulletYSpeed(), bulletSpeed, context, 2, size / 5));
                    running = false;
                } catch (Exception ignored) {
                }
            }
            interrupt();
        }
    }
}
