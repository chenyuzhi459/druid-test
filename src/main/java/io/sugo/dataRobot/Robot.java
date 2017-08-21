package io.sugo.dataRobot;

import org.joda.time.DateTime;

import java.io.*;
import java.util.Properties;

/**
 * Created by qwe on 17-7-22.
 */
public class Robot {
    public static void main(String[] args) throws Exception {
        int sum = 0;
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/data.properties"));

        DataCreator creator = new DataCreator();
        int numPerDay = Integer.parseInt(props.getProperty("numPerDay"));
        int numDay = Integer.parseInt(props.getProperty("numDay"));


        File file = new File(props.getProperty("savePath"));



        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if(!file.exists()) {
            System.out.println("created...");
            file.createNewFile();
        }


        FileWriter fileWriter = new FileWriter(file);

        DateTime start = new DateTime(2017,5,1,8,0);
        DateTime current;

        for(int j=0;j<numDay;j++) {
            current = start.plusDays(j);
            for(int i=0;i<numPerDay;i++) {

                String sendStr = creator.generateData( sum , current);
                fileWriter.write(sendStr+"\r\n");

                sum++;

                if( (sum) % 10000 == 0) {
                    System.out.println("send count:"+ sum);
                }

            }
        }

        System.out.println("send total:" + sum);

        fileWriter.close();
    }

}
