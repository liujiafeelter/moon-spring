package com.moon.store.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 1.发送并忘记 2.同步发送 3.异步发送+回调函数
 */
public class product {

    private static KafkaProducer<String,String> producer;

    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "152.136.187.50:9092");//ProducerConfig.BOOTSTRAP_SERVERS_CONFIG
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(properties);
    }

    /**
     * 1.发送并忘记
     */
    public static void send1() {
        ProducerRecord record = new ProducerRecord<String, String>("topic-order1", "userName", "yueliang");
        producer.send(record);
        producer.flush();
        System.out.println("result:" + record.toString());
    }

    /**
     * 2.同步发送
     */
    public static void send2() throws Exception{
        ProducerRecord record = new ProducerRecord<String, String>("test", "userName2", "yueliang");
        RecordMetadata result = (RecordMetadata) producer.send(record).get();
        System.out.println("result:" + record.toString());
    }

    public static void main(String[] args) throws Exception{
        for(int i=0; i<100; i++){
            send1();
        }
        producer.close();
    }


}
