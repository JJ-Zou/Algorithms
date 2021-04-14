package com.zjj.kafka.connetc;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;
import java.util.Scanner;

public class TestConnect {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "39.105.65.104:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(properties);
//        producer.send(new ProducerRecord<>())
        new Scanner(System.in).nextLine();
    }
}
