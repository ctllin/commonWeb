tar -cvf 192.168.43.3.zookeeper.conf.tar conf/
tar -cvf 192.168.43.3.zookeeper.data.tar --exclude=data/version-2 data/
tar -cvf 192.168.43.3.kafka.config.tar config/


tar -cvf 192.168.43.29.zookeeper.conf.tar conf/
tar -cvf 192.168.43.29.zookeeper.data.tar --exclude=data/version-2 data/
tar -cvf 192.168.43.29.kafka.config.tar config/