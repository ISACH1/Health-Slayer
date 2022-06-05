package ru.kirillisachenko.virusgame;

public class MathGenerator {

    public float DeltaDistance(float x1, float x2, float y1, float y2) {
        return (float) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }


    public int getRandom(int max, int min) {
        int random = 0;
        while (random == 0)
            random = (int) (Math.random() * (max - min) + min);
        return random;
    }

    public char getRandomSign() {
        double a = getRandom(3, 0);
        if (a == 1) {
            return '+';
        }
        return '-';
    }
}
