package ru.kirillisachenko.virusgame.gameItems;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public abstract class Item {
    protected MathGenerator mathGenerator;
    protected Hero hero;
    protected float xPosition;
    protected float yPosition;
    protected Bitmap Model;
    protected boolean NeedToRemove = false;

    public Item(float xPosition, float yPosition, Hero hero, Context context) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.hero = hero;
        mathGenerator = new MathGenerator();
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(Model, gameDisplay.gameToDisplayCoordinatesX(xPosition - getSize() / 2), gameDisplay.gameToDisplayCoordinatesY(yPosition - getSize() / 2), null);
    }

    public void update() {
        if (canTake()) take();
    }

    public float getSize() {
        return Model.getWidth();
    }

    protected boolean canTake() {
        return mathGenerator.DeltaDistance(xPosition, hero.getxPosition(), yPosition, hero.getyPosition()) <= hero.getSize();
    }

    public boolean isNeedToRemove() {
        return NeedToRemove;
    }

    public void take() {
        NeedToRemove = true;
    }
}
