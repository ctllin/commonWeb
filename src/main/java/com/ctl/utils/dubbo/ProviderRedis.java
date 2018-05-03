package com.ctl.utils.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 运行时需要启动zooKeeper
 */
public class ProviderRedis {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext( new String[] {"classpath*:/dubbo/providerRedis.xml"});
        context.start();
        // press any key to exit
        System.in.read();
    }
}