package ru.kirillisachenko.virusgame.gameobjects;


import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.MathGenerator;

public class GameObject {
    protected MathGenerator mathGenerator;
    protected float xPosition;
    protected float yPosition;
    protected double healthPoint;
    protected double maxHealthPoint;
    protected long lastAttack = 0;
    protected long attackSpeed;
    protected Bitmap[] Model;
    protected float xSpeed, ySpeed;
    protected float speed;
    protected int armor;
    protected double damage;
    protected boolean right;
    protected boolean thread;
    protected int size;

    public GameObject(float xPosition, float yPosition, int size) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        right = true;
        mathGenerator = new MathGenerator();
        Model = new Bitmap[8];
        thread = false;
        this.size = size;
    }

    public float getxPosition() {
        return xPosition;
    }

    public boolean canAttack() {
        return (System.currentTimeMillis() - lastAttack) >= attackSpeed;
    }

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
            canvas.drawBitmap(Model[2], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[2].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[2].getHeight() / 2), null);
        }
    }

    public float getyPosition() {
        return yPosition;
    }

    public int getSize() {
        return size;
    }

    public double getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(double health) {
        if (health >= maxHealthPoint) {
            this.healthPoint = maxHealthPoint;
            return;
        }
        if (health > 0) {
            this.healthPoint = health;
            return;
        }
        this.healthPoint = 0;
    }

    public double getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public void setMaxHealthPoint(double maxHealthPoint) {
        if (maxHealthPoint > 0) {
            this.maxHealthPoint = maxHealthPoint;
            return;
        }
        this.maxHealthPoint = 0;
    }


    public void takeDamage(double damage) {
        if (armor == 0) {
            setHealthPoint(getHealthPoint() - damage);
        }
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed * speed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed * speed;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public double getDamage() {
        return damage;
    }

    public void setAttackSpeed(long attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public long getAttackSpeed() {
        return attackSpeed;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    protected class DrawWalkThread extends Thread {
        private volatile boolean running = true;
        Canvas canvas;
        GameDisplay gameDisplay;

        public DrawWalkThread(Canvas canvas, GameDisplay gameDisplay) {
            this.canvas = canvas;
            this.gameDisplay = gameDisplay;
        }

        @Override
        public void run() {
            while (running) {
                if (xSpeed == 0) {
                    if (right) {
                        try {
                            canvas.drawBitmap(Model[0], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[0].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[0].getHeight() / 2), null);
                            sleep(33);
                            canvas.drawBitmap(Model[1], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[1].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[1].getHeight() / 2), null);
                            sleep(33);
                        } catch (Exception e) {
                        }
                    } else {
                        try {
                            canvas.drawBitmap(Model[2], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[2].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[2].getHeight() / 2), null);
                            sleep(33);
                            canvas.drawBitmap(Model[3], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[3].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[3].getHeight() / 2), null);
                            sleep(33);
                        } catch (Exception e) {
                        }
                    }
                }
                if (xSpeed > 0) {
                    try {
                        canvas.drawBitmap(Model[0], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[0].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[0].getHeight() / 2), null);
                        sleep(33);
                        canvas.drawBitmap(Model[4], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[4].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[4].getHeight() / 2), null);
                        sleep(33);
                        canvas.drawBitmap(Model[0], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[0].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[0].getHeight() / 2), null);
                        sleep(33);
                        canvas.drawBitmap(Model[5], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[5].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[5].getHeight() / 2), null);
                        sleep(33);
                        right = true;
                    } catch (Exception e) {
                    }
                }
                if (xSpeed < 0) {
                    try {
                        canvas.drawBitmap(Model[2], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[1].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[1].getHeight() / 2), null);
                        sleep(33);
                        canvas.drawBitmap(Model[6], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[6].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[6].getHeight() / 2), null);
                        sleep(33);
                        canvas.drawBitmap(Model[2], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[1].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[1].getHeight() / 2), null);
                        sleep(33);
                        canvas.drawBitmap(Model[7], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[7].getWidth() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[7].getHeight() / 2), null);
                        sleep(33);
                        right = false;
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}
