package io.sugo.dataRobot;


import org.joda.time.DateTime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by qwe on 17-6-13.
 */
public class FileOutput {


    public static void main(String[] args) throws IOException {

        try {
            File file = new File("druid-test-data");
            FileWriter fileWriter = new FileWriter(file);

            String s = "";

            for(int i=0;i<100;i++) {
                s += i +" ";
            }

            fileWriter.write(s);
            fileWriter.close(); // 关闭数据流
            System.out.println(s);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}





