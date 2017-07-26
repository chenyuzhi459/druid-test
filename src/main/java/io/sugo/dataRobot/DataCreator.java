package io.sugo.dataRobot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.joda.time.DateTime;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

/**
 * Created by qwe on 17-7-22.
 */
public class DataCreator implements Closeable{
        private static final ObjectMapper jsonMapper = new ObjectMapper();
        private static String mytopic = "testQuery";

        private final KafkaProducer<Integer, String> producer;
        private static final Random random = new Random();
        private static DateTime now;
        private static int i = 0;

        public DataCreator() throws IOException {
            Properties props = new Properties();
            props.load(new FileInputStream("test-kafka.properties"));

            mytopic = props.getProperty("topic");
            producer = new KafkaProducer<>(props);
        }

        @Override public void close() throws IOException {
            producer.close();
        }

        public void send(String msg) {
            producer.send(new ProducerRecord<Integer, String>(mytopic, msg));
        }





        public String generateData() throws JsonProcessingException {
              String id = Util.getMd5Sum(i+"");
//              String sex = U

//            return jsonMapper.writeValueAsString(maps);
            return "";
        }


}
