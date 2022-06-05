package ru.kirillisachenko.virusgame;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        int b = 0;
        MathGenerator mathGenerator = new MathGenerator();
        for (int i = 0; i < 40; i++) {
            int a = mathGenerator.getRandom(3, 0);
            System.out.println(a);
            if (a == 1) b++;
        }
        System.out.println(b);
    }


}