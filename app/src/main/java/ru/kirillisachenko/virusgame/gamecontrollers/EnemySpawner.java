package ru.kirillisachenko.virusgame.gamecontrollers;

import android.content.Context;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.gameobjects.Boss;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;
import ru.kirillisachenko.virusgame.gameobjects.Enemy;
import ru.kirillisachenko.virusgame.gameobjects.Jam_package.Jam;
import ru.kirillisachenko.virusgame.gameobjects.doctor_package.Doctor;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;
import ru.kirillisachenko.virusgame.gameobjects.pane_doctor_package.PaneDoctor;

public class EnemySpawner {
    private MathGenerator mathGenerator;
    private ArrayList<Enemy> enemyArrayList;
    private ArrayList<Boss> bosses;
    private int waveNumber = 1;
    private int BossWaveNumber = 1;
    private int numberOfBosses = 1;
    private int numberOfEnemies = 4;
    private boolean item;
    private Context context;
    private Hero hero;
    private ArrayList<Bullet> bullets;
    private int size;
    private int boss_size;
    private float range;

    public EnemySpawner(ArrayList<Enemy> enemyArrayList, Context context, Hero hero, ArrayList<Boss> bosses, ArrayList<Bullet> bullets, int size, int boss_size, float range) {
        this.enemyArrayList = enemyArrayList;
        this.context = context;
        this.hero = hero;
        mathGenerator = new MathGenerator();
        this.bosses = bosses;
        this.bullets = bullets;
        item = true;
        this.size = size;
        this.boss_size = boss_size;
        this.range = range;
    }


    public void update() {
        if (enemyArrayList.isEmpty() && bosses.isEmpty()) {
            spawn();
        }
    }

    public void spawn() {
        if ((waveNumber % 4) != 0) {
            for (int i = 0; i < numberOfEnemies; i++) {
                int type = mathGenerator.getRandom(101, -1);
                if (item) {
                    if (type > 40)
                        enemyArrayList.add(new PaneDoctor(getRandomCoordinate(hero.getxPosition()), getRandomCoordinate(hero.getyPosition()), size, context, hero, item, range));
                    if (type <= 40)
                        enemyArrayList.add(new Doctor(getRandomCoordinate(hero.getxPosition()), getRandomCoordinate(hero.getyPosition()), size, context, hero, item, range));
                } else {
                    if (type > 40)
                        enemyArrayList.add(new PaneDoctor(getRandomCoordinate(hero.getxPosition()), getRandomCoordinate(hero.getyPosition()), size, context, hero, mathGenerator.getRandom(101, -1) <= 30, range));
                    if (type <= 40)
                        enemyArrayList.add(new Doctor(getRandomCoordinate(hero.getxPosition()), getRandomCoordinate(hero.getyPosition()), size, context, hero, mathGenerator.getRandom(101, -1) <= 30, range));
                }
                item = false;
            }
        } else {
            for (int i = 0; i < numberOfBosses; i++) {
                bosses.add(new Jam(getRandomCoordinate(hero.getxPosition()), getRandomCoordinate(hero.getyPosition()), boss_size, context, hero, bullets, true, range));
                BossWaveNumber++;
            }
        }
        item = true;
        waveNumber++;
        numberOfEnemies += 2;
    }

    public float getRandomCoordinate(float coordinate) {
        char sign = mathGenerator.getRandomSign();
        if (sign == '+') return coordinate + mathGenerator.getRandom(1600, 799);
        return coordinate - mathGenerator.getRandom(1600, 799);
    }
}
