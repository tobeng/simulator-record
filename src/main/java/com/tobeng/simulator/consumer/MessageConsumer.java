package com.tobeng.simulator.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @package com.tobeng.simulator.Consumer
 * @date 2018/11/8
 */
@Slf4j
@Component
public class MessageConsumer {

    @KafkaListener(topics = "${spring.kafka.consumer.topic}")
    public void onMessage(ConsumerRecord<?, ?> record) {
        log.info("MessageConsumer: onMessage: message is: [" + record.value().toString() + "]");
    }
}
