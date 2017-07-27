package io.sugo.dataRobot;

import java.util.Random;

/**
 * Created by qwe on 17-7-27.
 */
public class Test01 {
    public static void main(String[] args) {

        for(int i=0;i<100;i++){
            Random random = new Random(i);
            System.out.println(random.nextInt(2) == 0? "男":"女");
        }
    }

}
