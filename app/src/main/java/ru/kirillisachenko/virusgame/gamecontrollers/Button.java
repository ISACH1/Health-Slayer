package ru.kirillisachenko.virusgame.gamecontrollers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.R;

public class Button {
    private int type;
    MathGenerator mathGenerator;
    private float xPosition, yPosition, rad;
    private boolean isPressed;
    private Bitmap buttonOn, buttonOff;


    public Button(float x, float y, float rad, Context context, int type) {
        this.type = type;
        this.xPosition = x;
        this.yPosition = y;
        this.rad = rad;
        mathGenerator = new MathGenerator();
        if (type == 0) {
            buttonOn = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fire_button_2), (int) rad, (int) rad, false);
            buttonOff = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.fire_button_1), (int) rad, (int) rad, false);
        }
        if (type == 1) {
            buttonOn = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_button_2), (int) rad, (int) rad, false);
            buttonOff = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ability_button_1), (int) rad, (int) rad, false);
        }
    }


    public boolean isInButton(float x, float y) {
        return mathGenerator.DeltaDistance(x, xPosition, y, yPosition) <= rad - buttonOn.getWidth() / 2;
    }

    public void draw(Canvas canvas) {
        if (!isPressed)
            canvas.drawBitmap(buttonOff, xPosition - buttonOff.getWidth() / 2, yPosition - buttonOff.getHeight() / 2, null);
        if (isPressed)
            canvas.drawBitmap(buttonOn, xPosition - buttonOn.getWidth() / 2, yPosition - buttonOn.getHeight() / 2, null);
    }

    public void drawKD(Canvas canvas) {
        canvas.drawBitmap(buttonOn, xPosition - buttonOn.getWidth() / 2, yPosition - buttonOn.getHeight() / 2, null);
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
    }
}
