tar -cvf 192.168.43.3.zookeeper.conf.tar conf/
tar -cvf 192.168.43.3.zookeeper.data.tar --exclude=data/version-2 data/
tar -cvf 192.168.43.3.kafka.config.tar config/


tar -cvf 192.168.43.29.zookeeper.conf.tar conf/
tar -cvf 192.168.43.29.zookeeper.data.tar --exclude=data/version-2 data/
tar -cvf 192.168.43.29.kafka.config.tar config/

启动zookeeper集群  192.168.42.29:2181,192.168.42.29:2182,192.168.42.29:2183
home/soft/cluster-zookeeper-3.5.3-beta/zookeeperStart.sh
#/home/soft/zookeeper-3.5.3-beta/bin/zkServer.sh start
#/home/soft/zookeeper-3.5.3-beta/bin/zkServer.sh status
启动kafka集群192.168.42.3/192.168.42.29
/home/soft/kafka_2.12-1.1.0/bin/kafka-server-start.sh /home/soft/kafka_2.12-1.1.0/config/server.properties &
//查询所有的topic
/home/soft/kafka_2.12-1.1.0/bin/kafka-topics.sh --zookeeper 192.168.42.29:2181,192.168.42.29:2182,192.168.42.29:2183 --list
在其中一台kafka上执行 topic创建
 ./kafka-topics.sh  --create  --zookeeper 192.168.42.29:2181,192.168.42.29:2182,192.168.42.29:2183 --replication-factor 1 --partitions 4  --topic ordercall
查看指定topic信息
bin/kafka-topics.sh --zookeeper 192.168.42.29:2181,192.168.42.29:2182,192.168.42.29:2183 --describe --topic  ordercall
查看所有topic列表
bin/kafka-topics.sh --zookeeper 192.168.42.29:2181,192.168.42.29:2182,192.168.42.29:2183 --list
