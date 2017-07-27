package io.sugo.dataRobot;

import java.util.Random;

/**
 * Created by qwe on 17-7-27.
 */
public class Test01 {
    public static void main(String[] args) {
        Random random = new Random();
        for(int i=0;i<100;i++) {
            System.out.println(random.nextFloat());
        };
    }

}
