package ru.kirillisachenko.virusgame.gameobjects;

import android.content.Context;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public abstract class Boss extends Enemy {

    protected ArrayList<Bullet> bullets;
    protected Context context;

    public Boss(float xPosition, float yPosition, int size, Context context, Hero hero, ArrayList<Bullet> bullets, boolean dropItem, float attackRange) {
        super(xPosition, yPosition, size, context, hero, dropItem, attackRange);
        this.bullets = bullets;
        this.context = context;
    }

    public ArrayList<Bullet> getList(){
        return bullets;
    }


    @Override
    public Bullet attack(Context context) {
        return null;
    }

    public abstract void Attack();

    @Override
    public boolean canAttack() {
        return (System.currentTimeMillis() - lastAttack) >= attackSpeed;
    }
}
