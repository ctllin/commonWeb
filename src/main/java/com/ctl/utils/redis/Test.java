package com.ctl.utils.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;


/**  
* <p>Title: Test.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.hanshow.com</p>  
* @author guolin 
* @date Apr 18, 2018  
* @version 1.0  
*/
public class Test {
	public static RedisTemplate redis;
	public static ValueOperations valueOperations;

	public static void main(String[] args) {
		ApplicationContext context = null;// new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		context = new ClassPathXmlApplicationContext("classpath:/spring/spring-redis-cluster.xml");
		redis = (RedisTemplate) context.getBean("redisTemplate");
		valueOperations = redis.opsForValue();
		valueOperations.set("1", "1");
		System.out.println(valueOperations.get("1"));
	}

}
