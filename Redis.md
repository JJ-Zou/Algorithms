
#### 数据类型

redis本身是一种key-value形式的数据库，key都是string类型，数据类型讨论的是value的类型

##### string
可以作为字符串和数值类型
```shell script
set key value
mset key1 value1 key2 value2
setex key seconds value #设置过期时间
psetex key milliseconds value
setnx key value #仅当key不存在时设值为value，可用于分布式锁
get key
mget key1 key2
del key
append key value #如果key存在就在原value后添加，如果可以不存在就创建
incr key #对数字自增
incrby key increment
incrbyfloat key increment #小数增长，使用后value变为字符串
decr key
decrby key decrement
```

##### list
存储多个数据，双向链表，可重复

##### set
不可重复无序

##### hash
可以在key中存储filed-value，只能是字符串，不允许嵌套。rehash类似ConcurrentHashMap，渐进式rehash，rehash的时候将数据分布到两个表中，每次操作都会帮助进行rehash，查询操作到各自的表上。

##### zset
有序集合，可根据特征排序，使用skip list实现

#### redis持久化

- rdb数据快照：每隔一段时间保存redis的全量快照，恢复时按数据恢复，恢复速度快，保存速度慢，无法做到是实时持久化。save手动执行阻塞，bgsave fork子进程执行。可以复制快照到其他服务器从而创建副本。
- aof写命令：以日志的方式记录每次写命令，重启时重新执行aof中的命令恢复数据，可以保证持久化的实时性，默认使用aof持久化。可以设置选项设置同步到文件的时机。

#### 分布式锁

使用setnx key value，尝试获取锁，成功返回1，失败返回0，使用expire key seconds或pexpire key millseconds为锁设置超时时间防止出现死锁或饥饿

#### redis事务

一个事务可以包含多个命令，开启事务`multi`，执行事务`exec`，取消事务`discard`。

#### 删除策略

当达到最大内存后，挑选数据进行删除

- 检测易失数据，对于设置了过期时间的
  - volatile-lru：最近最少使用
  - volatile-lfu：最近使用次数最少
  - volatile-ttl：过期时间最短
  - volatile-random：随机
- 全局数据
  - allkeys-lru
  - allkeys-lfu
  - allkeys-random
- 禁止删除数据

#### redis为什么快

- 纯内存操作
- IO多路复用，和redis自身基于事件处理的模型
- 单线程避免上下文切换，和竞争

#### 缓存穿透、缓存雪崩、缓存击穿

- 缓存穿透：指查询数据库中不存在的数据，导致缓存和数据库都没有命中。可以采用缓存空结果或者布隆过滤器解决。布隆过滤器采用多个hash函数将结果保存到位图。
- 缓存雪崩：同一时刻有大量缓存失效，大量并发到达数据库。可以通过设置不同的过期时间，部署高可用集群或限流降级对查询加锁。
- 缓存击穿：热点数据突然失效。可以设置热点数据永不过期，或者在访问数据时更新过期时间，使用分布式锁控制查询数据库的线程数。