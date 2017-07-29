package io.sugo.dataRobot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.joda.time.DateTime;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by qwe on 17-7-22.
 */
public class DataCreator implements Closeable{
        private static final ObjectMapper jsonMapper = new ObjectMapper();
        private static String mytopic = "testQuery";

        private final KafkaProducer<Integer, String> producer;
        private static DateTime now;

        public DataCreator() throws IOException {
            Properties props = new Properties();
            props.load(new FileInputStream("kafka.properties"));

            mytopic = props.getProperty("topic");
            producer = new KafkaProducer<>(props);
        }

        @Override public void close() throws IOException {
            producer.close();
        }

        public void send(String msg) {
            producer.send(new ProducerRecord<Integer, String>(mytopic, msg));
        }



        public static Random random;

        public String generateData(int i,DateTime dateTime) throws JsonProcessingException {
            random = new Random(i);
            //id
            String id = Util.getMd5Sum(i+"");
            //性别
            String sex = random.nextInt(100) % 2 == 0? "男":"女";
            //年龄
            int age = random.nextInt(50) + 15;
            //省份
            String province = DataConst.province[random.nextInt(34)];
            //时间
            DateTime dt = dateTime.plusMillis(random.nextInt(1000 * 60 * 60 * 24));
            //薪资
            float salary = random.nextFloat() * 10000 + 3000;
            //double
            double size = random.nextDouble();
            //long
            long longNum = Math.abs(random.nextLong());

            List list = new ArrayList();
            list.add(id);
            list.add(sex);
            list.add(age);
            list.add(province);
            list.add(dt.getMillis());
            list.add(salary);
            list.add(size);
            list.add(longNum);

            return getCsvFromList(list);

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
