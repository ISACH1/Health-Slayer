package ru.kirillisachenko.virusgame.gameItems;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public class DamageUp extends Item{
    public DamageUp(float xPosition, float yPosition, Hero hero, Context context) {
        super(xPosition, yPosition, hero, context);
        Model = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.damage_up), 90, 90,  false);
    }

    @Override
    public void take() {
        super.take();
        hero.setDamage(hero.getDamage() + 0.5);
    }
}
