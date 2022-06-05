package ru.kirillisachenko.virusgame.gameItems;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public class AttackSpeedUp extends Item{
    public AttackSpeedUp(float xPosition, float yPosition, Hero hero, Context context) {
        super(xPosition, yPosition, hero, context);
        Model = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.attack_speed_up), 100, 100,  false);
    }

    @Override
    public void take() {
        super.take();
        hero.setAttackSpeed((long) (hero.getAttackSpeed() - hero.getAttackSpeed()*0.1));
    }
}
