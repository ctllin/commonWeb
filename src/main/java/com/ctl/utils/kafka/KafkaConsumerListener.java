package com.ctl.utils.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

/**
 * <p>Title: KafkaConsumerListener</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-11-30 16:01
 */
public class KafkaConsumerListener implements MessageListener<Integer, String> {
    Logger logger = LoggerFactory.getLogger(KafkaConsumerListener.class);
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord) {
        Object o = consumerRecord.value();
        System.out.println(String.valueOf(o));
    }
}
