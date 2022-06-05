package ru.kirillisachenko.virusgame.gameobjects;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;


import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.gameItems.AttackSpeedUp;
import ru.kirillisachenko.virusgame.gameItems.DamageUp;
import ru.kirillisachenko.virusgame.gameItems.HealthUp;
import ru.kirillisachenko.virusgame.gameItems.Item;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public abstract class Enemy extends GameObject {
    protected MathGenerator mathGenerator;
    protected Hero hero;
    protected float attackRange;
    protected float minDistance;
    protected boolean dropItem;
    protected int score;

    public Enemy(float xPosition, float yPosition, int size, Context context, Hero hero, boolean dropItem, float attackRange) {
        super(xPosition, yPosition, size);
        this.hero = hero;
        armor = 0;
        this.dropItem = dropItem;
        this.minDistance = attackRange;
        mathGenerator = new MathGenerator();
    }

    public void update() {
        float distance = mathGenerator.DeltaDistance(hero.getxPosition(), xPosition, hero.getyPosition(), yPosition);
        float deltaX = hero.getxPosition() - xPosition;
        float deltaY = hero.getyPosition() - yPosition;
        if ((int) Math.abs(Math.floor(distance)) > (int) minDistance) {
            xSpeed = deltaX / distance * speed;
            ySpeed = deltaY / distance * speed;
        }
        if ((int) Math.abs(Math.floor(distance)) < (int) minDistance) {
            xSpeed = -deltaX / distance * speed;
            ySpeed = -deltaY / distance * speed;
        }
        if (Math.abs(minDistance - distance) <= 2) {
            xSpeed = 0;
            ySpeed = 0;
        }
        xPosition += xSpeed;
        yPosition += ySpeed;
    }

    public abstract Bullet attack(Context context);

    @Override
    public boolean canAttack() {
        return mathGenerator.DeltaDistance(hero.getxPosition(), xPosition, hero.getyPosition(), yPosition) <= attackRange && (System.currentTimeMillis() - lastAttack) >= attackSpeed;
    }

    public float getBulletXSpeed() {
        float distance = mathGenerator.DeltaDistance(hero.getxPosition(), xPosition, hero.getyPosition(), yPosition);
        return (hero.getxPosition() - xPosition) / distance;
    }

    public float getBulletYSpeed() {
        float distance = mathGenerator.DeltaDistance(hero.getxPosition(), xPosition, hero.getyPosition(), yPosition);
        return (hero.getyPosition() - yPosition) / distance;
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        if (xSpeed > 0) {
            canvas.drawBitmap(Model[0], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[0].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[0].getHeight() / 2), null);
            Model[2] = Model[0];
        }
        if (xSpeed < 0) {
            canvas.drawBitmap(Model[1], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[1].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[1].getHeight() / 2), null);
            Model[2] = Model[1];
        }
        if (xSpeed == 0) {
            if (hero.getxPosition() > xPosition)
                canvas.drawBitmap(Model[0], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[0].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[0].getHeight() / 2), null);
            else
                canvas.drawBitmap(Model[1], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[1].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[1].getHeight() / 2), null);
        }
    }

    public Item die(Context context) {
        hero.setScore(hero.getScore() + score);
        if (dropItem) {
            int type = mathGenerator.getRandom(4, 0);
            switch (type) {
                case (1):
                    return new AttackSpeedUp(xPosition, yPosition, hero, context);
                case (2):
                    return new HealthUp(xPosition, yPosition, hero, context);
                case (3):
                    return new DamageUp(xPosition, yPosition, hero, context);
            }

        }
        return null;
    }
}
