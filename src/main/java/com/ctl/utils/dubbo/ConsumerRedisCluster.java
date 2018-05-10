package com.ctl.utils.dubbo;

import com.ctl.web.dubbo.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 运行时需要启动redis ,需要执行Provider或者启动tomcat服务，启动tomcat前需要已经启动redis
 * 如果服务的在但是redis停止服务,依旧可以访问接口  3 mater 5 slave
 * redis://192.168.42.29:6379?backup=192.168.42.29:6380,192.168.42.29:6381 如果6381关闭这时6383会变成master  节点尽量不小于6 3mater slave>=3 */
public class ConsumerRedisCluster {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"/dubbo/consumerRedisCluster.xml"});
        context.start();
        // obtain proxy object for remote invocation
        HelloService helloService = (HelloService) context.getBean("helloService");
        // execute remote invocation
        String hello = helloService.say("world");
        // show the result
        System.out.println(hello);
        context.close();
    }
}