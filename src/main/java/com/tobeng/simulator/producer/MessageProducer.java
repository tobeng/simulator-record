package com.tobeng.simulator.producer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @package com.tobeng.simulator.MessageProducer
 * @date 2018/11/8
 */
@Slf4j
@Component
public class MessageProducer {

    @Value("${spring.kafka.producer.topic}")
    private String topic;
    @Autowired
    private KafkaTemplate kafkaTemplate;
    private Gson gson = new GsonBuilder().create();

    public void send(Object object) {
        String msg = gson.toJson(object);
        kafkaTemplate.send(topic, msg);
        log.info("MessageProducer: send: message is: [" + msg + "]");

    }

    public void send(String message){
        kafkaTemplate.send(topic, message);
        log.info("MessageProducer: send: message is:{}" + message);
    }

}
