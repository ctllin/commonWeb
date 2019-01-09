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