package ru.kirillisachenko.virusgame;

import android.graphics.Rect;

import ru.kirillisachenko.virusgame.gameobjects.GameObject;

public class GameDisplay {
    public final Rect DISPLAY_RECT;
    private final float widthPixels;
    private final float heightPixels;
    private final GameObject centerObject;
    private final float displayCenterX;
    private final float displayCenterY;
    private float gameToDisplayCoordinatesOffsetX;
    private float gameToDisplayCoordinatesOffsetY;
    private float gameCenterX;
    private float gameCenterY;

    public GameDisplay(float widthPixels, float heightPixels, GameObject centerObject) {
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        DISPLAY_RECT = new Rect(0, 0, (int) widthPixels, (int) heightPixels);

        this.centerObject = centerObject;

        displayCenterX = (float) (widthPixels / 2.0);
        displayCenterY = (float) (heightPixels / 2.0);

        update();
    }

    public void update() {
        gameCenterX = centerObject.getxPosition();
        gameCenterY = centerObject.getyPosition();

        gameToDisplayCoordinatesOffsetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinatesOffsetY = displayCenterY - gameCenterY;
    }

    public float gameToDisplayCoordinatesX(float x) {
        return x + gameToDisplayCoordinatesOffsetX;
    }

    public float gameToDisplayCoordinatesY(float y) {
        return y + gameToDisplayCoordinatesOffsetY;
    }

    public Rect getGameRect() {
        return new Rect(
                (int) (gameCenterX - widthPixels / 2),
                (int) (gameCenterY - heightPixels / 2),
                (int) (gameCenterX + widthPixels / 2),
                (int) (gameCenterY + heightPixels / 2)
        );
    }
}