# Rabbit MQ

消息队列中间件（Erlang语言开发，实现AQMP协议）

两种传递模式：点到点

​							发布/订阅模式（一对多广播）

提供了基于存储和转发的异步数据发送。

## 消息中间件的作用

解耦：

冗余：

扩展性：

可恢复性：

顺序保证：

缓冲：

异步通信：



## RabiitMQ的安装

先安装Erlang语言

```
wget http://erlang.org/download/otp_src_21.1.tar.gz
tar -zxvf otp_src_21.1.tar.gz
cd otp_src_21.1
# 这里要新建一个erlang文件夹，因为erlang编译安装默认是装在/usr/local下的bin和lib中，这里我们将他统一装到/usr/local/erlang中，方便查找和使用。
mkdir -p /usr/local/erlang
# 在编译之前，必须安装以下依赖包
yum install -y make gcc gcc-c++ m4 openssl openssl-devel ncurses-devel unixODBC unixODBC-devel java java-devel
 
./configure --prefix=/usr/local/erlang
```

直接执行make && make install 进行编译安装

安装后，在/usr/local/erlang中就会出现如下：ls 出现bin和lib目录。

配置环境变量

```
vim /etc/profile
#########   添加如下内容   ###############
 export PATH=$PATH:/usr/local/erlang/bin
########################################
 
source /etc/profile
```

验证安装完成，输入 erl

OK，安装完erlang后，下面安装rabbitmq，安装之前，需要去官网查看一下rabbitmq版本对erlang版本的一个支持情况，官网地址：http://www.rabbitmq.com/which-erlang.html

```
# 下载源码包
wget https://github.com/rabbitmq/rabbitmq-server/releases/download/v3.7.8/rabbitmq-server-generic-unix-3.7.8.tar.xz
# 解压
tar -xvf rabbitmq-server-generic-unix-3.7.8.tar.xz -C /usr/local/
# 添加环境变量
vim /etc/profile
------  添加如下内容  ------
PATH=$PATH:/usr/local/rabbitmq_server-3.7.8/sbin
 
 
# 重载一下环境变量
source /etc/profile
 
# 添加web管理插件
rabbitmq-plugins enable rabbitmq_management
 
```

```
# 后台启动rabbitmq服务
rabbitmq-server -detached
```

有一个web管理界面，默认监听端口15672,默认用户名密码guest/guest

查看Rabbitmq运行状态

```
rabbitmqctl status
```

查看集群信息

```
rabbitmqctl cluster_status
```

### 生成和消费消息

maven的pom文件

```
<dependency>
	<groupId>com.rabbitmq</groupId>
	<artifactId>amqp-client</artifactId>
	<version>4.2.1</version>
</dependency>
```

给客户端添加新用户

```
 rabbitmqctl add_user root root123
 # 设置权限
 rabbitmqctl set_permissions -p / root ".*" ".*" ".*"
# 设置root用户管理员角色
rabbitmqctl set_user_tags root administrator
```



消息生产者

```
public class RabbitProducer {
    private static final String EXCHANGE_NAME="exchange_demo";
    private static final String ROUTING_KEY="routingkey_demo";
    private static final String QUEUE_NAME="queue_demo";
    private static final String IP_ADDRESS="192.168.3.140";
    private static final int PORT=5672;

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("root");
        factory.setPassword("root123");
        Connection connection=factory.newConnection();//创建连接
        Channel channel=connection.createChannel();//创建信道
        //创建一个type=direct持久化的非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        //创建一个持久化的，非排他的，非自动删除的队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        //发送一条持久化消息
        String message="Hello RabbitMQ";
        channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        channel.close();
        connection.close();
    }
}

```

消息消费者

```
package com.dl.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author : lhk
 * @Description :
 * @Date : 2020/9/9 17:48
 */
public class RabbitConsumer {
    private static final String QUEUE_NAME="queue_demo";
    private static final String IP_ADDRESS="192.168.3.140";
    private static final int PORT=5672;

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Address[] addresses=new Address[]{
                new Address(IP_ADDRESS,PORT)
        };
        ConnectionFactory factory=new ConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("root123");
        Connection connection=factory.newConnection(addresses);
        final Channel channel=connection.createChannel();
        //设置客户端最多接受未被ack的个数
        channel.basicQos(64);
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("recv message: "+new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_NAME,consumer);
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();
    }
}

```

## 入门指南

### 架构图

![image-20200910144503656](img\rabbit1.png)

### 生产者和消费者

Producer：投递消息的一方

消息包含两部分，消息体和标签

消费者：接受消息的一方

Broker：消息中间件的服务节点



生产者封装数据（序列化）发送到Broker中（AMQP协议中的Basic.publish），消费者订阅并接受消息（AMQP协议中对应命令Basic.Consumer或者Basic.get）

### 队列

Queue：是RabbitMQ的内部对象，用于存储消息。。

多个消费者可以订阅同一个队列，队列会平均分消息（轮询）分给消费者，不是每个消费者都得到所有消息。



RabbitMQ不支持队列层面的广播消费。

### 交换器，路由键，绑定

交换器（四种类型）：生产者生产消息，先到交换器，再有交换器路由到一个或者多个队列上。



路由键RoutingKey:生产者生产消息到交换器的时候一般会指定路由键，用来指定路由规则，key需要与交换区和绑定键联合使用才能生效。



绑定Banding:RabbitMQ通过绑定关联交换器和队列，在绑定的时候一般会指定一个绑定键。



生产者将消息交给交换器的时候，需要一个RoutingKey，当BindingKey和RoutingKey相匹配的时候，消息会被路由到对应的队列，。



交换器相当于投递包裹的邮箱，RoutingKey相当于包裹上的地址。BindingKey相当于包裹的目的地，当包裹上地址和包裹的实际目的地相同时才能进入队列。

### 交换器类型

**fanout**：他会把所有发送到该交换器的消息路由到**所有**与该交换器绑定的队列中。

**direct**：他会把消息路由到Routingkey和BindingKey完全匹配的队列中。

![image-20200910151405956](\img\rabbit2.png)

**topic**:在BindingKey和RoutingKey的匹配规则上进行了扩展，

约定：RoutingKey为一个点号“.”进行分割的字符串，例如com.rabbitmq.client

BindingKey和RoutingKey一样都是点号分割的字符串。

BindingKey可以存在两种特殊符号，“*”用于匹配一个单词，“#”用于匹配多个单词，可以是0个。

如图：路由键“com.rabbitmq.client”能分配到Queue1和Queue2上

路由键为“com.hidden.client”的消息只会路由到Queue2上

路由键为“com.hidden.demo”的消息只会路由到Queue2上



![image-20200910152936650](\img\rabbit3.png)

**headers**：headers类型不依赖与路由键和绑定键匹配来发送消息，根据消息内容中的headers属性进行匹配。根据headers中的key，value是否匹配队列和交换器绑定时的键。

### RabbitMQ运转流程

#### 生产者发送消息

（1）生产者连接到RabbitMQ Broker建立一个连接（Connection），开启一个信道（Channel）。

（2）生产者声明一个交换器，并设置属性，比如交换器的类型，是否持久化等等。

（3）生产者声明一个队列，并设置属性，是否排他，是否持久，是否自动删除

（4）生产者通过路由键将交换器和队列绑定起来。

（5）生产者发送消息到RabbitMQ Broker，其中包含路由键，交换器等等。

（6）相应的交换器收到路由键查找相匹配的队列。

（7）如果找到，则将生产者发送过来的消息存入相应的队列。

（8）如果没有找到，则根据生产者配置的属性选择丢弃还是回退给生产者。、

（9）关闭信道

（10）关闭连接

#### 消费者消费消息

（1）消费者连接到RabbitMQ Broker，建立连接，开启一个信道。

（2）消费者向RabbitMQ Broker请求消费一个相应队列的消息，可能会设置相应的回调函数。

（3）等待RabbitMQ Broker回应并投递相应队列的消息，消费者接受消息。

（4）消费者确认接到消息（ack）

（5）RabbitMQ Broker删除相应的已经被确认的消息

（6）关闭信道

（7）关闭连接



多个信道可以共用一条连接，每个线程对应一条信道。



## AMQP协议

AMQP协议本身分三层：

Moudle Layer:位于协议的最高层，主要定义了一些客户端的命令，客户端利用这些命令实现自己的业务逻辑。例如客户端使用Queue.Declare命令声明一个队列，或者使用Basic.Consume订阅消费一个队列的消息。



Session Layer:位于中间层，主要负责客户端命令发送给服务器，再将服务器应答返回客户端，主要为客户端和服务器之间通信提供可靠性同步机制和错误处理。



Transport Layer:位于最底层，主要传输二进制流，提供帧处理，信道复位，错误检测。



## 客户端开发向导

### 连接RabbitMQ

需要用到的参数（ip地址，端口，用户名，密码等）

```
 ConnectionFactory factory=new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("root");
        factory.setPassword("root123");
        Connection connection=factory.newConnection();//创建连接
```

通过uri创建连接

```
 ConnectionFactory factory=new ConnectionFactory();
 factory.setUri("amqp://userName:password@ipAddress:portNumber/varxxx");
 Connection conn=factory.newConnection();
 Channel channel=conn.createChannel();
```

### 使用交换器和队列

使用之前先声明

```
channel.exchangeDeclare(exchangeName,"direct",true);
String queueName=channel.queueDeclare().getQueue();
channel.queueBind(queueName,exchangeName,routingKey);
//创建一个持久化，非自动删除，绑定类型是direct的交换器
//创建一个非持久化，排他的，自动删除的队列
```

### 消费消息

分两种模式，推模式和拉模式，推模式采用Basic.Consume进行消费，拉模式采用Basic.get()

### 推模式

通过持续订阅方式来消费

```
booean autoAck=false;
channel.basicQos(64);
channel.basicConsume(QueueName,autoAck,"MyConsumerTag",new DefaultConsumer(channel){
	@Override
	public void handleDelivery(String consumerTag,Envelope envilope,AMQP.BasicProperties properties,byte[] body){
	String routingKey=envelope.getRoutingKey();
	String contentType=properties.getContentType();
	long delivery =envelope.getDeliveryTag();
	channel.basicAck(deliveryTag,false);
	}
})
```

### 拉模式

```
GetResponse response=channel.basicGet(Queue_Name,false);
sout(response.getBody());
channel.basicAck(response.getEnvelope().getDeliveryTag(),false);
```

### 消费端的确认与拒绝

autoAck为false的时候，需要手动消费端进行确认，发送Basic.ack确认命令，队列才会删除。

autoAck为true的时候。自动确认收到消息。自动删除

拒绝消息：Basic,basicNack一次只能拒绝一个消息。Basic.Nack能批量拒绝。

## RabbitMQ进阶

### 消息何去何从

mandatory和immediate是channel.basicPublish方法中的俩个参数。

#### mandatory参数

当mandatory参数设为true时，交换器无法根据自身的类型和路由键找到一个符合条件的队列，那么RabbitMQ会调用Basic.Return命令将消息返回给生产者。当mandatory参数设置为false时，出现上述情形，则消息直接被丢弃。  



通过channel.addReturnListener来添加ReturnListener监听器实现。

#### immediate参数

为true的时候，如果交换器在将消息路由到队列时发现队列上不存在任何消费者，那么消息不会存入队列，通过Basic.Return返回给生产者。

### 过期时间TTL

第一种方式，通过队列的属性进行设置，队列中所有消息都有相同的过期时间。

第二种方式：对单条消息进行过期时间设置，。

消息队列中的消息一旦超过了TTL的时间，消息就成了死信。消费者将无法消费。



如果不设置TTL就代表消息永远不会过期。如果设置为0代表可以直接投递给消费者，否则消息就会被丢弃。



针对每条消息的TTL设置channel.basicPublish的expiration属性参数，单位为毫秒。

#### 设置队列的TTL过期时间

通过channel.queueDeclare方法的x-expires参数设置，可以设置在未自动删除前处于未使用状态的时间。



### 死信队列

DLX被称为死信交换器。当消息变成死信之后他会被重新发送到死信交换器DLX，绑定死信交换器的队列叫做死信队列。



消息变成死信的几种情况？

1 消息被拒绝

2 消息过长

3 消息过期

4 队列达到最大长度。

### 延迟队列

延迟队列存储的对象是延迟消息。

延迟消息是不想进入队列立即被消费者消费，而是等待一段时候后在被消费。

### 优先级队列

具有更高优先级的队列优先消费。

### RPC实现

RPC是远程过程调用。

### 持久化

队列的持久化在声明的时候设置属性为true实现。

### 生产者确认

在使用RabbitMQ的时候，通过消息持久化来解决服务器的异常崩溃而导致的消息丢失。

生产者确认的两种方案：

1 通过事务机制实现

2 通过发送方确认机制。

#### 事务机制

客户端的三个方法：

1 channel.txSelect 用于将当前信道设置成事务模式

2 channel.txCommit  提交事务

3 channel.txRallback   回滚事务

#### 发送方确认机制

生产者将信道设置成confirm（确认）模式，一旦信道进入confirm模式，所有在该信道上面发布的消息都会被指派一个唯一的ID（从1开始），一旦消息被投递到所有匹配的队列之后，RabbitMQ就会发送一个确认（Basic.Ack）给生产者（包含消息的唯一ID），这就使得生产者知晓消息已经正确到达了目的地了。如果消息和队列是持久化的，那么确认消息会在消息写入磁盘之后发出。RabbitMQ回传给生产者的确认消息中的deliveryTag包含了确认消息的序号，此外RabbitMQ也可以设置channel.basicAck方法中的multiple参数，表示到这个序号之前的所有消息都已经得到了处理，  



### 消费端的要点

#### 消息分发

当RabbitMQ队列拥有多个消费者的时候，会将队列的消息轮询发给消费者。每条消息只会发送给队列的一个消费者。

通过channel.basicQos(int aa)限制信道上消费者所能保持的最大未确认消息数。

#### 消息顺序性

消费者消费消息的顺序和生产者生产的消息的顺序是一致的。

死信，多生产者，多消费者，事务回滚会影响消息的顺序性。

可以使用唯一的id序列做顺序处理。



#### 消息传输保障

最多一次：消息可能丢失，但是不会重传。

最少一次：消息绝不会丢失，但是可能重传。

恰好一次：每条消息肯定会传输一次仅传输一次。



## RabbitMQ管理

### 多租户和权限

每一个RabbitMQ服务器都能创建虚拟的消息服务器，我们称谓虚拟主机vhost。每个客户端连接的时候必须指定一个vhost



创建一个虚拟机主机服务，rabbitmqctl add_vhost vhost1

删除 rabbitctl delete_vhosts vhost1

授权：rabbitmqctl set_permissions [-p vhost] {user} {conf} {write} {read}

conf是资源路径。write写权限的目录

清除权限：rabbitmqctl clear_permissions -p vhost root

### 用户管理

用户是访问控制的最小单元

创建用户：rabbitmqctl add_user root root123

改密码：rabbitmactl change_password root root321

清除密码：rabbitmqctl clear_password root

验证用户：rabbitmqctl authenticate_user {username} {password}

删除用户：rabbitmqctl delete_user root

查看所有用户：rabbitmqctl list_user

用户角色的5种类型：

none：无任何角色，默认

management:可以访问web管理页面

policymaker:包含了management的所有权限，可以管理策略（Policy）和参数

monitoring:包含management的所有权限，可以看到所有连接，信道以及节点的信息。

administrator:最高权限，可以管理用户，虚拟主机，权限，策略参数等。



设置角色：rabbitmqctl  set_user_tags root administrator

