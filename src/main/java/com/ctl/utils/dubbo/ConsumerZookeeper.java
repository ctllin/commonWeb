package com.ctl.utils.dubbo;

import com.ctl.web.dubbo.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 运行时需要启动zooKeeper ,需要执行Provider或者启动tomcat服务，启动tomcat前需要已经启动zookeeper 如果配置了集群至少启动三个zookeeper  而且活动的zookeeper至少为2个(测试时当杀死多个zookeeper只剩下一个会报错)
 * leader死掉之后需要过一点时间才会执行成功(follower 转leader需要时间)
 */
public class ConsumerZookeeper {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"/dubbo/consumerZookeeper.xml"});
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