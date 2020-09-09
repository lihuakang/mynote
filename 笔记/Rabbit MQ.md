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

