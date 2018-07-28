package com.ctl.utils.kafka;

import com.ctl.utils.DateUtil;
import net.sf.json.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;

import java.util.*;

/**
 * 需要安装环境
 * 启动ZooKeeper(进入/home/soft/kafka_2.12-1.0.0 目录下面执行)
 打开一个新终端并键入以下命令 -
 bin/zookeeper-server-start.sh config/zookeeper.properties
 要启动Kafka Broker，请键入以下命令 -
 bin/kafka-server-start.sh config/server.properties
 启动Kafka Broker后，在ZooKeeper终端上键入命令 jps ，您将看到以下响应 -
 为了局域网可以访问 在config中的server.properties需要修改下面两条
 listeners=PLAINTEXT://192.168.42.29:9092
 advertised.listeners=PLAINTEXT://192.168.42.29:9092

 如果生产者早于消费者启动，消费者启动后需要过一会才能获取到数据（如果消费者已经启动，那么生产者产生数据那么消费者基本很快获取到数据）
 同一个分组下不同的group.name  如果生产者产生数据，但是消费者分组只有一个启动，那个该消费者会消费该数据，其他消费者启动后，就无法消费生产者产生的数据，可以消费下次生产者产生的数据

 821 QuorumPeerMain
 928 Kafka
 931 Jps
 */
public class ProducerDemo {
    public static String topic="top";
    public static Integer num=10;
    public static String serverHost="192.168.42.29:9091,192.168.42.29:9092,192.168.42.29:9093";
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", serverHost);
        //The "all" setting we have specified will result in blocking on the full commit of the record, the slowest but most durable setting.
        //“所有”设置将导致记录的完整提交阻塞，最慢的，但最持久的设置。
        props.put("acks", "all");
        //如果请求失败，生产者也会自动重试，即使设置成０ the producer can automatically retry.
        props.put("retries", 0);

        //The producer maintains buffers of unsent records for each partition.
        props.put("batch.size", 16384);
        //默认立即发送，这里这是延时毫秒数
        props.put("linger.ms", 1);
        //生产者缓冲大小，当缓冲区耗尽后，额外的发送调用将被阻塞。时间超过max.block.ms将抛出TimeoutException
        props.put("buffer.memory", 33554432);
        //The key.serializer and value.serializer instruct how to turn the key and value objects the user provides with their ProducerRecord into bytes.
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class", "org.apache.kafka.clients.producer.internals.DefaultPartitioner");

        //创建kafka的生产者类
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        //生产者的主要方法
        // close();//Close this producer.
        //   close(long timeout, TimeUnit timeUnit); //This method waits up to timeout for the producer to complete the sending of all incomplete requests.
        //  flush() ;所有缓存记录被立刻发送
        for (int i = 0; i < num; i++) {
             //这里平均写入４个分区 第一步 在kaifa config 中的 server.properties 中配置为 num.partitions=4
            // 第二步 ./kafka-topics.sh  --create  --zookeeper 192.168.42.29:2181,192.168.42.3:2181 --replication-factor 1 --partitions 4  --topic foo
            // ./kafka-topics.sh --delete --zookeeper 192.168.42.29:2181,192.168.42.3:2181 --topic foo
             //producer.send(new ProducerRecord<String, String>("foo", i % 4, Integer.toString(i), Integer.toString(i)));
             //因为没有建立集群所以只能是0个分区
            Person person = new Person();
            person.setAge(27);
            person.setId(UUID.randomUUID().toString());
            person.setName("ctl"+ DateUtil.sdfyyyy_MM_dd_HH_mm_ss.format(new Date()));
            person.setRemark("ich liebe dich");
            person.setSalary(18.99d);
            person.setStatus((byte)1);
             producer.send(new ProducerRecord<String, String>(topic, i % 4, Integer.toString(i), JSONObject.fromObject(person).toString()));
        }
        List<PartitionInfo> partitions = new ArrayList<PartitionInfo>() ;
        partitions = producer.partitionsFor(topic);
        for(PartitionInfo p:partitions)
        {
            System.out.println(p);
        }
        // bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic foo  删除foo主题
        producer.close();
    }
}