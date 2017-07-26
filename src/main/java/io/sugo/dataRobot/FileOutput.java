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

    public static DecimalFormat df = new DecimalFormat("0.00");
    public static Random random = new Random();

    public static void main(String[] args) throws IOException {
        DataCreator creator = new DataCreator();

        try {
            File file2 = new File("/tmp/test/testNormal");
            FileWriter fileWriter = new FileWriter(file2);

            String s = "";

            for(int i=0;i<100;i++) {
                if(i<99) {
                    s += creator.generateData() + "\r\n";
                } else{
                    s += creator.generateData();
                }
            }

            fileWriter.write(s);
            fileWriter.close(); // 关闭数据流
            System.out.println(s);


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public static String generateData() {
        StringBuffer sb = new StringBuffer();

        DateTime dt = new DateTime();

        for(int i=0;i<100;i++) {
            //sb.append( i + "," + dt.minusMillis(new Random().nextInt(1000 * 24 * 30)).getMillis() +"," + Util.getMd5Sum(i+"") + "\r\n"  );
            sb.append( dt.minusMillis(new Random().nextInt(1000 * 24 * 30)).getMillis() + "," + i +"," + Util.getMd5Sum(i+"") + "\r\n"  );
        }
        return sb.toString();

    }

}





