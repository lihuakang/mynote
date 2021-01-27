# Redis

### redis可以做什么？

1、 记录帖子的点赞数、 评论数和点击数 (hash)。  

2、 记录用户的帖子 ID 列表 (排序)， 便于快速显示用户的帖子列表 (zset)。  

3、 记录帖子的标题、 摘要、 作者和封面信息， 用于列表页展示 (hash)。
4、 记录帖子的点赞用户 ID 列表， 评论 ID 列表， 用于显示和去重计数 (zset)。
5、 缓存近期热帖内容 (帖子内容空间占用比较大)， 减少数据库压力 (hash)。
6、 记录帖子的相关文章 ID， 根据内容推荐相关帖子 (list)。
7、 如果帖子 ID 是整数自增的， 可以使用 Redis 来分配帖子 ID(计数器)。
8、 收藏集和帖子之间的关系 (zset)。
9、 记录热榜帖子 ID 列表， 总热榜和分类热榜 (zset)。
10、 缓存用户行为历史， 进行恶意行为过滤 (zset,hash)。  

## 基础

### redis安装

去reids官网查找下载
centos安装**wget http://download.redis.io/releases/redis-4.0.0.tar.gz**
tar -zxvf XXX解压
进入目录安装
**make && make install先编译在安装**。进入到src目录
**redis-server启动服务器**

进入客户端，在src下
**redis-cli进入客户端**
安装完成！！！！

### Redis基础数据结构

5中基础数据结构，stirng（字符串）list（列表）set（无序集合）hash（哈希） sortedSet（有序集合）

#### string字符串

字符串 string 是 Redis 最简单的数据结构。 Redis 所有的数据结构都是以唯一的 key字符串作为名称，然后通过这个唯一 key 值来获取相应的 value 数据。不同类型的数据结构的差异就在于 value 的结构不一样  

内部为当前字符串实际分配的空间 capacity 一般要高于实际字符串长度 len。当字符串长度小于 1M 时，扩容都是加倍现有的空间，如果超过 1M，扩容时一次只会多扩 1M 的空间。需要注意的是字符串最大长度为 512M。  

```
127.0.0.1:6379> setex name 5 ll
设置name 5秒钟失效
OK
127.0.0.1:6379> get name
"ll"
127.0.0.1:6379> get name
(nil)
127.0.0.1:6379> setnx name code
如果name存入则设置不成功，如果不存在设置成功
(integer) 1
127.0.0.1:6379> setnx name code
(integer) 0
127.0.0.1:6379> set age 30
OK
127.0.0.1:6379> incr age
age是int类型，每次自增1
(integer) 31
127.0.0.1:6379> incr age
(integer) 32
127.0.0.1:6379> incr age 2
(error) ERR wrong number of arguments for 'incr' command
127.0.0.1:6379> incrby age 5
每次自增不是1
(integer) 37
127.0.0.1:6379> incrby age 5
(integer) 42
127.0.0.1:6379> exists name
判断key是否存在
(integer) 1
127.0.0.1:6379> exists name2
(integer) 0
127.0.0.1:6379>expire name 5
设置key失效时间，单位为s

```

字符串是由多个字节组成，每个字节又是由 8 个 bit 组成，如此便可以将一个字符串看成很多 bit 的组合，这便是 bitmap「位图」数据结构  

#### list列表

相当于java中的linkedList，也就是增加和删除的时间复杂度为O(1)

定位的时间复杂度为O(n)

模拟队列（右边进左边出）

```
127.0.0.1:6379> rpush books python java golang
右边进队列
(integer) 3
127.0.0.1:6379> llen books
books长度
(integer) 3
127.0.0.1:6379> lpop books
左边出队列
"python"
127.0.0.1:6379> lpop books
"java"
127.0.0.1:6379> lpop books
"golang"
127.0.0.1:6379> lpop books
(nil)
127.0.0.1:6379>

```

模拟栈（右边进右边出）

```
127.0.0.1:6379> exists books
(integer) 0
127.0.0.1:6379> rpush books python java golang
(integer) 3
127.0.0.1:6379> rpop books
"golang"
127.0.0.1:6379> rpop books
"java"
127.0.0.1:6379> rpop books
"python"
127.0.0.1:6379>
```

**慢操作**
lindex相当于java链表的get(int index)需要堆链表的遍历
ltrim 保留 为ltrim的两个参数start_index 和end_index定义了一个区间，在区间内保留，区间外删除，可以通过Itrim实现一个定长的链表

index可以定义为负数 ，-1表示倒数第一个数 ，-2表示倒数第二个数

```
127.0.0.1:6379> rpush books python java golang
(integer) 3
127.0.0.1:6379> lindex books 1
"java"
127.0.0.1:6379> lrange books 0 -1
1) "python"
2) "java"
3) "golang"
127.0.0.1:6379> ltrim books 1 -1
OK
127.0.0.1:6379> lrange books 0 -1
1) "java"
2) "golang"
127.0.0.1:6379> ltrim books 1 0
清空整个列表，因为区间是负数
OK
127.0.0.1:6379> llen books
(integer) 0
127.0.0.1:6379>
```

#### hash字典

Redis 的字典相当于 Java 语言里面的 HashMap，它是无序字典。内部实现结构上同Java 的 HashMap 也是一致的，同样的数组 + 链表

二维结构。第一维 hash 的数组位置碰撞时，就会将碰撞的元素使用链表串接起来。  

![image-20210112151944265](img\redis1.png)

不同的是， Redis 的字典的值只能是字符串  

```
127.0.0.1:6379> hset books java "think in java"
命令行有空格所有要加双引号
(integer) 1
127.0.0.1:6379> hset books golang "concurrency in go"
(integer) 1
127.0.0.1:6379> hset books python "python cookbook"
(integer) 1
127.0.0.1:6379> hgetall books
获取所有books里面的kv
1) "java"
2) "think in java"
3) "golang"
4) "concurrency in go"
5) "python"
6) "python cookbook"
127.0.0.1:6379> hlen books
(integer) 3
127.0.0.1:6379> hget books java
"think in java"
127.0.0.1:6379> hset books golang "learning go programming"
更新，所以返回0
(integer) 0
127.0.0.1:6379> hget books golang
"learning go programming"
127.0.0.1:6379> hmset books java "effective java" python "learning python" golang "modern golang programming"
批量更新返回ok
OK
127.0.0.1:6379>

```

#### set集合

相当于java中的HashSet,内部的键值对是无序的，唯一的
可以存储中将用户ID，因为有去重功能

```
127.0.0.1:6379> sadd books python
(integer) 1
127.0.0.1:6379> sadd books python
重复 设置失败
(integer) 0
127.0.0.1:6379> sadd books java golang
(integer) 2
127.0.0.1:6379> smembers books
无序的
1) "golang"
2) "python"
3) "java"
127.0.0.1:6379> sismember books java
查看一个key是否存在
(integer) 1
127.0.0.1:6379> sismember books rust
(integer) 0
127.0.0.1:6379> scard books
查看set的长度
(integer) 3
127.0.0.1:6379> spop books
随机弹出一个
"python"
127.0.0.1:6379>
```

####   zset列表（有序列表）

类似于java中的SortedSet和HashMap的结合体
他是一个set保证内部的value唯一性，另一方面给value赋予一个score，代表权重，内部实现是一种叫做 跳跃列表的数据结构。
zset最后一value被删除时，数据结构会自动删除，内存被回收

zset可以存储粉丝列表，value值是粉丝的用户id，score是关注的时间，我们可以对粉丝列表按关注顺序排序

zset可以存储学生的成绩，value值是学生的id，score是它考试的成绩，按成绩排序得到名次

```
 zadd books 9.0 "think in java"
(integer) 1
127.0.0.1:6379> zadd books 8.9 "java concurrency"
(integer) 1
127.0.0.1:6379> zadd books 8.6 "java cookbook"
(integer) 1
127.0.0.1:6379> zrange books 0 -1
按score排序列出
1) "java cookbook"
2) "java concurrency"
3) "think in java"
127.0.0.1:6379> zrevrange books 0 -1
按score逆序列出
1) "think in java"
2) "java concurrency"
3) "java cookbook"
127.0.0.1:6379> zcard books
(integer) 3
127.0.0.1:6379> zscore books "java concurrency"
查看权重，内部使用double
"8.9000000000000004"
127.0.0.1:6379> zrank books "java concurrency"
排名
(integer) 1
127.0.0.1:6379> zrank books "java cookbook"
(integer) 0
127.0.0.1:6379> zrangebyscore books 0 8.91
根据分值区间遍历
1) "java cookbook"
2) "java concurrency"
127.0.0.1:6379> zrangebyscore books -inf 8.91 withscores
根据分值区间遍历 inf代表无穷大
1) "java cookbook"
2) "8.5999999999999996"
3) "java concurrency"
4) "8.9000000000000004"
127.0.0.1:6379> zrem books "java concurrency"
删除value
(integer) 1
127.0.0.1:6379> zrange books 0 -1
1) "java cookbook"
2) "think in java"

```

#### *跳跃列表

zset 内部的排序功能是通过「跳跃列表」数据结构来实现的，它的结构非常特殊，  

![image-20210112152703838](img\redis2.png)

L0 层肯定是 100% 了， L1 层只有 50% 的概率， L2 层只有 25% 的概率， L3层只有 12.5% 的概率，一直随机到最顶层 L31 层。  



**如果一个字符串已经设置了过期时间，然后你调用了set 方法修改了它，它的过期时间会消失。**  



## 分布式锁

setnx(set if not exists)
setnx key value
设置分布式锁，防止死锁，设置key的过期时间



为了防止setnx和expire之间的服务器进程突然挂掉，导致无法释放锁，不能用redis事务来解决。
redis2.8中加入了set指令的扩展指令，就是setnx和expire组合在一起的原子操作。

```
127.0.0.1:6379> set lock:codehole true ex 5 nx
OK
127.0.0.1:6379> get lock:codehole
"true"
127.0.0.1:6379> get lock:codehole
(nil)

```

