package io.sugo.dataRobot;

import java.io.IOException;

/**
 * Created by qwe on 17-7-22.
 */
public class Robot {

    public static void main(String[] args) throws IOException {
        DataCreator creator = new DataCreator();
        for(int i=0;i<100;i++) {
//            String msg = creator.generateDataCsv();
//            creator.send(msg);
//            System.out.println(creator.generateDataCsv());
        }

        creator.close();
;
    }
}
