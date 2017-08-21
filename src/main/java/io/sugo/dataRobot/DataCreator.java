package io.sugo.dataRobot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.joda.time.DateTime;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;

import java.math.BigDecimal;
import java.security.MessageDigest;

import java.util.*;

/**
 * Created by qwe on 17-7-22.
 */

public class DataCreator{

    private static MessageDigest md ;
        public DataCreator() throws Exception {
            Properties props = new Properties();
            props.load(new FileInputStream("src/main/resources/data.properties"));
            md = MessageDigest.getInstance("MD5");
        }



        public static Random random;

        public String generateData(int i,DateTime dateTime) throws JsonProcessingException {
            random = new Random(i);


            //标识
            long id = i + 1;
            //String id_string = getMd5String(id + "");
            //年龄
            int age = random.nextInt(50) + 15;
            //省份
            String province = DataConst.province[random.nextInt(34)];
            //时间
            DateTime dt = dateTime.plusMillis(random.nextInt(1000 * 60 * 60 * 24));

            long minDate = new DateTime(2017,5,1,8,0).getMillis();
            long maxDate = new DateTime(2017,5,31,8,0).getMillis();
            if(dt.getMillis() < minDate || dt.getMillis() > maxDate ){
                throw new RuntimeException("时间越界");
            }
            //生日
            DateTime birthday = dt.minusYears(age).plusMillis(random.nextInt(1000 * 60 * 60 * 24 * 365));
            //薪资
            long salary = random.nextInt(20000)  + 3000;
            //平均分
            float average = random.nextInt(100) + random.nextFloat();
            //身高
            double height = random.nextInt(70) + 130 + random.nextDouble();

            int dimension1 = random.nextInt(100);
            int dimension2 = random.nextInt(50) + 100;
            //int regularDim = getRegularDimension(random);
            //体重
            //BigDecimal weight = new BigDecimal(random.nextInt(50) + 30 +random.nextDouble());

            List list = new ArrayList();
            list.add(id);
            //list.add(id_string);
            list.add(age);
            list.add(province);
            list.add(dt.getMillis());
            list.add(birthday.getMillis());
            list.add(salary);
            list.add(height);
            //list.add(weight);
            list.add(average);
            list.add(dimension1);
            list.add(dimension2);
            //list.add(regularDim);

            return getCsvFromList(list);

        }


        public static String  getMd5String(String message){
            StringBuffer sb = new StringBuffer();
            md.reset();
            md.update(message.getBytes());
            byte[] digest = md.digest();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        }

        public static int getRegularDimension(Random random){
            int flag = random.nextInt(100);
            if(flag<10){
                return random.nextInt(50);
            }else if(flag<40) {
                return random.nextInt(50) + 50;
            }else if(flag<=100){
                return random.nextInt(50) + 100;
            }
            return -1;
        }


        public static String getCsvFromList(List list) {
            StringBuffer csvStr = new StringBuffer();
            Iterator iterator = list.iterator();
            while(iterator.hasNext()) {
                csvStr.append(iterator.next() + ",");
            }
            return csvStr.substring(0,csvStr.length()-1);
        }

}
