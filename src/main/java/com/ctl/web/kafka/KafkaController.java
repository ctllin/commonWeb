package com.ctl.web.kafka;

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
    @Resource
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @RequestMapping(value = "/hello")
    public void hello(){
        kafkaTemplate.sendDefault("test it"+new Date());
    }

}
