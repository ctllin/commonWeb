package com.ctl.web.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @RequestMapping(value = "/hello")
    public void hello(){
        logger.info("kafka/hello is excute");
       // kafkaTemplate.sendDefault("test it");
       // kafkaTemplate.send("test",1,"1");
        kafkaTemplate.send("test", "1");

    }

}
