#1、密码设置(推荐)
#修改所有Redis集群中的redis.conf文件加入： 
masterauth liebe 
requirepass liebe 
#rm -rf *.aof && rm -rf  dump63* && rm -rf nodes-63* 
#2、修改  client.rb
 find / -name 'client.rb'
 例如：/usr/local/share/gems/gems/redis-4.0.1/lib/redis/client.rb
 class Client
    DEFAULTS = {
      :url => lambda { ENV["REDIS_URL"] },
      :scheme => "redis",
      :host => "127.0.0.1",
      :port => 6379,
      :path => nil,
      :timeout => 5.0,
      :password => nil,
      :db => 0,
      :driver => nil,
      :id => nil,
      :tcp_keepalive => 0,
      :reconnect_attempts => 1,
      :inherit_socket => false
    }
	修改为(password和masterauth，requirepass保持一致)：
	class Client
    DEFAULTS = {
      :url => lambda { ENV["REDIS_URL"] },
      :scheme => "redis",
      :host => "127.0.0.1",
      :port => 6379,
      :path => nil,
      :timeout => 5.0,
      :password => "liebe",
      :db => 0,
      :driver => nil,
      :id => nil,
      :tcp_keepalive => 0,
      :reconnect_attempts => 1,
      :inherit_socket => false
    }
#3、启动redis
   执行redisClusterStart
/home/soft/redis-4.0.7/src/redis-server /home/soft/redis-4.0.7/cluster/nodes-6379/redis.conf
/home/soft/redis-4.0.7/src/redis-server /home/soft/redis-4.0.7/cluster/nodes-6380/redis.conf
/home/soft/redis-4.0.7/src/redis-server /home/soft/redis-4.0.7/cluster/nodes-6381/redis.conf
/home/soft/redis-4.0.7/src/redis-server /home/soft/redis-4.0.7/cluster/nodes-6382/redis.conf
/home/soft/redis-4.0.7/src/redis-server /home/soft/redis-4.0.7/cluster/nodes-6383/redis.conf
/home/soft/redis-4.0.7/src/redis-server /home/soft/redis-4.0.7/cluster/nodes-6384/redis.conf
#4、创建集群
   redisCreateCulster
/home/soft/redis-4.0.7/src/redis-trib.rb create --replicas 1 192.168.42.29:6379 192.168.42.29:6380  192.168.42.29:6381 192.168.42.29:6382 192.168.42.29:6383 192.168.42.29:6384

 redis-cli -c -h 192.168.42.29 -p 6382         
连接集群加上-c参数，此选项可以防止moved和ask异常
#5、关闭redis集群后，只需要执行（redisClusterStart）即可不需要再执行（redisCreateCulster）

/home/soft/redis-4.0.7/src/redis-cli -c -h 192.168.42.29 -p 6379 -a liebe
/home/soft/redis-4.0.7/src/redis-cli -c -h 192.168.42.29 -p 6380 -a liebe
/home/soft/redis-4.0.7/src/redis-cli -c -h 192.168.42.29 -p 6381 -a liebe
/home/soft/redis-4.0.7/src/redis-cli -c -h 192.168.42.29 -p 6382 -a liebe
/home/soft/redis-4.0.7/src/redis-cli -c -h 192.168.42.29 -p 6383 -a liebe
/home/soft/redis-4.0.7/src/redis-cli -c -h 192.168.42.29 -p 6384 -a liebe
#6、检查集群状态进入其中一个
 cluster info
 集群
 cluster info ：打印集群的信息
 cluster nodes ：列出集群当前已知的所有节点（ node），以及这些节点的相关信息。
 节点
 cluster meet <ip> <port> ：将 ip 和 port 所指定的节点添加到集群当中，让它成为集群的一份子。
 cluster forget <node_id> ：从集群中移除 node_id 指定的节点。
 cluster replicate <node_id> ：将当前节点设置为 node_id 指定的节点的从节点。
 cluster saveconfig ：将节点的配置文件保存到硬盘里面。
 槽(slot)
 cluster addslots <slot> [slot ...] ：将一个或多个槽（ slot）指派（ assign）给当前节点。
 cluster delslots <slot> [slot ...] ：移除一个或多个槽对当前节点的指派。
 cluster flushslots ：移除指派给当前节点的所有槽，让当前节点变成一个没有指派任何槽的节点。
 cluster setslot <slot> node <node_id> ：将槽 slot 指派给 node_id 指定的节点，如果槽已经指派给
 另一个节点，那么先让另一个节点删除该槽>，然后再进行指派。
 cluster setslot <slot> migrating <node_id> ：将本节点的槽 slot 迁移到 node_id 指定的节点中。
 cluster setslot <slot> importing <node_id> ：从 node_id 指定的节点中导入槽 slot 到本节点。
 cluster setslot <slot> stable ：取消对槽 slot 的导入（ import）或者迁移（ migrate）。
 键
 cluster keyslot <key> ：计算键 key 应该被放置在哪个槽上。
 cluster countkeysinslot <slot> ：返回槽 slot 目前包含的键值对数量。
 cluster getkeysinslot <slot> <count> ：返回 count 个 slot 槽中的键  