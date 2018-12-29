package com.ctl.web.kafka.controller;

import com.ctl.utils.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>Title: KafkaController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-11-30 16:03
 */
@Controller
@RequestMapping(value="/kafka")
public class KafkaController {
    Logger logger = LoggerFactory.getLogger(KafkaController.class);
    @Resource
    private KafkaTemplate<Integer, String> kafkaTemplate;
    @Autowired
    private KafkaProducerListener producerListener;

    @RequestMapping(value = "/hello")
    public void hello() {
        logger.info("kafka/hello is excute");
        kafkaTemplate.setProducerListener(producerListener);
        kafkaTemplate.send(ConfigUtils.getType("kafka.defaultTopic"), "1");

    }

}
