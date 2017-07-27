package io.sugo.dataRobot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.joda.time.DateTime;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

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





        public String generateData(int i,DateTime dateTime) throws JsonProcessingException {
            Random random = new Random(i);
            //id
            String id = Util.getMd5Sum(i+"");
            //性别
            String sex = random.nextInt(2) == 0? "男":"女";
            //年龄
            int age = random.nextInt(50) + 15;
            //省份
            String province = DataConst.province[random.nextInt(34)];
            //时间
            DateTime dt = dateTime.plusMillis(random.nextInt(1000 * 60 * 24));
            //薪资
            float salary = random.nextFloat() * 10000 + 3000;
            //double
            double size = random.nextDouble();


            Map<String,Object> map = new HashMap<>();

            map.put("s|id",id);
            map.put("s|sex",sex);
            map.put("i|age",age);
            map.put("s|province",province);
            map.put("d|dt",dt.getMillis());
            map.put("f|province",province);
            map.put("d|dt",dt.getMillis());

            return jsonMapper.writeValueAsString(map);
        }


}
