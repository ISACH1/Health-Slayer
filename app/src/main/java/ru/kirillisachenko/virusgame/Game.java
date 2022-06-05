package ru.kirillisachenko.virusgame;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.Activities.LoseActivity;
import ru.kirillisachenko.virusgame.gameItems.Item;
import ru.kirillisachenko.virusgame.gamecontrollers.Button;
import ru.kirillisachenko.virusgame.gamecontrollers.EnemySpawner;
import ru.kirillisachenko.virusgame.gamecontrollers.HealthBar;
import ru.kirillisachenko.virusgame.gamecontrollers.Joystick;
import ru.kirillisachenko.virusgame.gameobjects.Boss;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;
import ru.kirillisachenko.virusgame.gameobjects.Enemy;
import ru.kirillisachenko.virusgame.gameobjects.Jam_package.Jam;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Classic.ClassicVirus;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Ninja.NinjaVirus;
import ru.kirillisachenko.virusgame.map.Room;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private Bitmap map;
    private SurfaceHolder holder;
    private HealthBar healthBar;
    private Button AutoFireButton, AbilityButton;
    private Room room;
    private GameDisplay gameDisplay;
    private Hero hero;
    private Joystick joystick1;
    private ArrayList<Boss> boss;
    private ArrayList<Enemy> enemyArrayList;
    private ArrayList<Bullet> heroBullets;
    private ArrayList<Bullet> enemyBullets;
    private ArrayList<Item> itemArrayList;
    private EnemySpawner enemySpawner;
    private GameThread gameThread;
    private int joystickPointerId1 = 0;
    private int autoFireButtonPointerID = 0;
    private int AbilityButtonID = 0;
    private int TouchCount = 0;
    private final int type;
    private final int fps = 60;

    public Game(Context context, int type) {
        super(context);
        getHolder().addCallback(this);
        this.type = type;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        setGameControllers();
        setHero();
        setArrayLists();
        this.holder = holder;
        setGameDisplay();
        setEnemySpawner();
        map = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.back), 10000, 10000, false);
        gameThread = new GameThread();
        gameThread.start();
    }

    public void setEnemySpawner() {
        enemySpawner = new EnemySpawner(enemyArrayList, getContext(), hero, boss, enemyBullets, getWidth() / 13, getWidth() / 3, (float) getWidth()/5);
    }

    public void setHero() {
        switch (type) {
            case (0):
                hero = new ClassicVirus(getContext(), 0, 0, getWidth() / 13, joystick1);
                break;
            case (1):
                hero = new NinjaVirus(getContext(), 0, 0, getWidth() / 13, joystick1);
                break;
        }
        healthBar = new HealthBar(0, 0, getWidth() / 20, getContext(), hero);
    }

    public void setGameControllers() {
        room = new Room(getWidth(), getHeight(), getContext());
        joystick1 = new Joystick(getWidth() / 8, getHeight() - getHeight() / 4, getWidth() / 9 - 25, getWidth() / 9, getContext());
        AutoFireButton = new Button(getWidth() - getWidth() / 5, getHeight() - getHeight() / 6, getWidth() / 9, getContext(), 0);
        AbilityButton = new Button(getWidth() - getWidth() / 10, getHeight() - getHeight() / 3, getWidth() / 9, getContext(), 1);
    }

    public void setGameDisplay() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, hero);
    }

    public void setArrayLists() {
        boss = new ArrayList<>();
        enemyArrayList = new ArrayList<>();
        heroBullets = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        itemArrayList = new ArrayList<>();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_POINTER_DOWN:
                if (AutoFireButton.isPressed()) {
                    if (joystick1.isInJoystick(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()))) {
                        joystickPointerId1 = event.getPointerId(event.getActionIndex());
                        joystick1.setPressed(true);
                        joystick1.setActuator(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()));
                    }
                }
                if (joystick1.isPressed()) {
                    if (AutoFireButton.isInButton(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()))) {
                        autoFireButtonPointerID = event.getPointerId(event.getActionIndex());
                        AutoFireButton.setPressed(true);
                    }
                    if (AbilityButton.isInButton(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()))) {
                        AbilityButtonID = event.getPointerId(event.getActionIndex());
                        AbilityButton.setPressed(true);
                    }
                } else if (joystick1.isInJoystick(event.getX(event.getActionIndex()), (event.getY(event.getActionIndex())))) {
                    joystickPointerId1 = event.getPointerId(event.getActionIndex());
                    joystick1.setPressed(true);
                    joystick1.setActuator(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()));
                } else {
                    if (AutoFireButton.isInButton(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()))) {
                        AutoFireButton.setPressed(true);
                        autoFireButtonPointerID = event.getPointerId(event.getActionIndex());
                    }
                    if (AbilityButton.isInButton(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()))) {
                        AbilityButtonID = event.getPointerId(event.getActionIndex());
                        AbilityButton.setPressed(true);
                    }
                }
                TouchCount++;
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joystick1.isPressed()) {
                    float x = event.getX();
                    float y = event.getY();
                    if (TouchCount > 1) {
                        int i1 = event.findPointerIndex(event.getPointerId(0));
                        int i2 = event.findPointerIndex(event.getPointerId(1));
                        x = event.getX(i1);
                        y = event.getY(i1);
                        if (x > getWidth() / 2) {
                            x = event.getX(i2);
                            y = event.getY(i2);
                        }
                    }
                    joystick1.setActuator(x, y);
                }

                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (joystickPointerId1 == event.getPointerId(event.getActionIndex())) {
                    joystick1.setPressed(false);
                    joystick1.resetActuator();
                }
                if (autoFireButtonPointerID == event.getPointerId(event.getActionIndex())) {
                    AutoFireButton.setPressed(false);
                }
                if (AbilityButtonID == event.getPointerId(event.getActionIndex())) {
                    AbilityButton.setPressed(false);
                }

                TouchCount--;
                return true;
        }

        return true;
    }

    private class GameThread extends Thread {
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Canvas canvas = holder.lockCanvas();
                try {
                    Thread.sleep(1000/fps);
                    drawFrames(canvas, gameDisplay);
                    update();
                } catch (Exception e) {
                } finally {
                    try {
                        holder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        gameThread.running = false;
    }

    public void update() {
        enemySpawner.update();
        joystick1.update();
        hero.update();
        if (hero.getHealthPoint() == 0) {
            getContext().startActivity(new Intent(getContext(), LoseActivity.class).putExtra("SCORE", hero.getScore()).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            gameThread.running = false;
            gameThread.interrupt();
        }
        if (AutoFireButton.isPressed()) {
            if (hero.canAttack()) {
                if (!enemyArrayList.isEmpty()) {
                    heroBullets.add(hero.attack(enemyArrayList, getContext()));
                }
                if (!boss.isEmpty()) {
                    heroBullets.add(hero.attackBoss(boss, getContext()));
                }
            }
        }
        if (AbilityButton.isPressed()) {
            if (hero.canCast()) {
                hero.castAbility();
            }
        }
        for(Boss boss: boss){
            boss.update();
            if(boss.canAttack()){
                boss.Attack();
            }
        }
        for (Bullet bullet : heroBullets) {
            bullet.update();
            if (room.getXPoint(1, 1) >= bullet.getxPosition() - bullet.getSize() || room.getYPoint(1, 1) >= bullet.getyPosition() - bullet.getSize()
                    || room.getXPoint(98, 98) <= bullet.getxPosition() - bullet.getSize() || room.getYPoint(98, 98) <= bullet.getyPosition() - bullet.getSize())
                enemyBullets.remove(bullet);
        }
        for (Bullet bullet : enemyBullets) {
            bullet.update();
            if (room.getXPoint(1, 1) >= bullet.getxPosition() - bullet.getSize() || room.getYPoint(1, 1) >= bullet.getyPosition() - bullet.getSize()
                    || room.getXPoint(98, 98) <= bullet.getxPosition() - bullet.getSize() || room.getYPoint(98, 98) <= bullet.getyPosition() - bullet.getSize())
                enemyBullets.remove(bullet);
        }
        for (Enemy e : enemyArrayList) {
            e.update();
            if (e.canAttack()) {
                enemyBullets.add(e.attack(getContext()));
            }
        }
        for (Enemy enemy : enemyArrayList) {
            if (enemy.getHealthPoint() == 0) {
                if (enemy.die(getContext()) != null) {
                    itemArrayList.add(enemy.die(getContext()));
                }
                enemyArrayList.remove(enemy);
            }
            for (Bullet heroBullet : heroBullets) {
                if (heroBullet.Collision(heroBullet, enemy)) {
                    heroBullets.remove(heroBullet);
                    enemy.takeDamage(hero.getDamage());
                    break;
                }
            }
        }
        for (Boss boss1 : boss) {
            if (boss1.getHealthPoint() == 0) {
                boss.remove(boss1);
                itemArrayList.add(boss1.die(getContext()));
            }
            for (Bullet heroBullet : heroBullets) {
                if (heroBullet.Collision(heroBullet, boss1)) {
                    heroBullets.remove(heroBullet);
                    boss1.takeDamage(heroBullet.getDamage());
                    break;
                }
            }
        }

        for (Bullet enemyBullet : enemyBullets) {
            if (enemyBullet.Collision(enemyBullet, hero)) {
                enemyBullets.remove(enemyBullet);
                hero.takeDamage(enemyBullet.getDamage());
                break;
            }
        }
        for (Item item : itemArrayList) {
            item.update();
            if (item.isNeedToRemove()) itemArrayList.remove(item);
        }
        healthBar.update();
        gameDisplay.update();
    }

    public void drawFrames(Canvas canvas, GameDisplay gameDisplay) {
        room.draw(canvas, gameDisplay);
        canvas.drawBitmap(map, gameDisplay.gameToDisplayCoordinatesX(0), gameDisplay.gameToDisplayCoordinatesY(0), null);
        hero.draw(canvas, gameDisplay);
        for (Enemy e : enemyArrayList) {
            e.draw(canvas, gameDisplay);
        }
        for (Bullet bullet : heroBullets) {
            bullet.draw(canvas, gameDisplay);
        }
        for (Bullet bullet : enemyBullets) {
            bullet.draw(canvas, gameDisplay);
        }
        for (Boss b : boss) {
            b.draw(canvas, gameDisplay);
        }
        for (Item item : itemArrayList) item.draw(canvas, gameDisplay);
        joystick1.draw(canvas);
        AutoFireButton.draw(canvas);
        if (!hero.canCast()){
            AbilityButton.drawKD(canvas);
        } else{
            AbilityButton.draw(canvas);
        }
        healthBar.draw(canvas);
    }
}
