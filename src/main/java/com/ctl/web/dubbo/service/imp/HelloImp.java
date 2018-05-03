package com.ctl.web.dubbo.service.imp;

import com.ctl.web.dubbo.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value="helloService")
public class HelloImp implements HelloService{
    Logger logger= LoggerFactory.getLogger(HelloService.class);

    @Override
    public String say(String name) {
        logger.info(name);
        return "HelloImp say:"+name;
    }
}
