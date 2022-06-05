package ru.kirillisachenko.virusgame.gamecontrollers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import ru.kirillisachenko.virusgame.R;

public class Joystick {
    private float xB, yB, oRad, xM, yM, actuatorX, actuatorY;
    private boolean isPressed = false;
    private Bitmap buttonIn, buttonOut;

    public Joystick(float xB, float yB, float iRad, float oRad, Context context) {
        this.xB = xB;
        this.yB = yB;
        this.oRad = oRad;
        this.xM = xB;
        this.yM = yB;
        buttonIn = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.button_in), (int) iRad, (int) iRad, false);
        buttonOut = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.button_out), (int) oRad + buttonIn.getWidth() / 2, (int) oRad + buttonIn.getHeight() / 2, false);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(buttonOut, xB - buttonOut.getWidth() / 2, yB - buttonOut.getHeight() / 2, null);
        canvas.drawBitmap(buttonIn, xM - buttonIn.getWidth() / 2, yM - buttonIn.getHeight() / 2, null);
    }

    public void update() {
        xM = xB + actuatorX * oRad;
        yM = yB + actuatorY * oRad;
    }


    public boolean isInJoystick(float x, float y) {
        float distance = (float) Math.sqrt(Math.pow((xB - x), 2) + Math.pow((y - yB), 2));
        return distance <= oRad;
    }

    public void setActuator(float xA, float yA) {
        float deltaX = xA - xB;
        float deltaY = yA - yB;
        float delta = (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        if (delta < oRad) {
            actuatorX = deltaX / oRad;
            actuatorY = deltaY / oRad;
        } else {
            actuatorX = deltaX / delta;
            actuatorY = deltaY / delta;
        }
    }

    public float getActuatorX() {
        return actuatorX;
    }

    public float getActuatorY() {
        return actuatorY;
    }


    public void resetActuator() {
        actuatorX = 0;
        actuatorY = 0;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }
}
