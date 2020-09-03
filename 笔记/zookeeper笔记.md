zookeeper笔记

## zookeeper的概括和基础

### zookeeper能做什么

zookeeper可以在分布式中协作多个任务

使用场景：在Apache HBase中使用zookeeper选举主节点

Kafka中用于检测崩溃

Solr中使用zookeeper存储集群数据

### zookeeper的功能

保障强一致性，有序性和持久性

实现通用的同步原语的能力

zookeeper提供并发处理机制

### zookeeper不适用的场景

1 不适合做海量数据的存储

### 分布式进程之间通信

分布式系统中进程之间通信有两种：网络间进行信息交换，或者读写某些共享存储。

zookeeper使用共享存储实现进程间通信，和同步原语。

## zookeeper基础

### 什么是原语

计算机进程的控制通常由原语完成。所谓原语，一般是指由若干条指令组成的程序段，用来实现某个特定功能，在执行过程中不可被中断。

### zookeeper的结构

zookeeper是一个树状结构，维护一个小型的数据节点znode

![image-20200826143703406](img\zookeeper1.png)

./workers节点作为一个父节点。其下每个znode子节点保存了系统中可用的子节点信息。有一个从节点（foo.com:2181）

./tasks作为父节点，其下每个子节点保存了所有已经创建并等待从节点执行任务的信息。在./tasks节点下的子节点，表示一个新任务，等待任务状态的znode节点。

./assign（分配）节点作为父节点。其下每个znode保存了从节点分配到某个节点的任务信息。



### API

```
create /path data

创建一个名为/path的znode，数据是data

delete /path

删除名为/path的znode

exists /path

检查是否存在/path的znode

setData /path data

设置名为/path的znode数据为data

getData /path
返回名为/path节点的数据

getChildren/path
返回所有/path节点的所有子节点列表

```

### znode的不同类型

持久节点和临时节点

持久节点只能通过delete来进行删除

临时znode与之相反，当客户端崩溃或者关闭zookeeper连接时，临时节点会被删除。



当主节点创建znode为临时节点，如果主节点消失后，该znode仍存在，如果时持久节点，就无法检测主节点崩溃。

### 临时节点被删除的情况

1 当创建znode的客户端会话因为超时或者主动关闭而中断

2 某个客户端主动删除该节点。

不允许临时节点有子节点

### 有序节点

会给znode分配一个序号

/tasks/task-1

### znode 四种类型节点

持久的

临时的

持久有序的

临时有序的

## 监视和通知

客户端向zookeeper注册需要通知的znode。通过对znode设置监视点（watch）来接受通知。

监视点是一个单次触发的操作。客户端必须在每次通知后设置一个新的监视点。



因为通知机制是单词触发操作，所以客户端接受一个znode变更通知并设置新的监视点时，znode也许发生了新的变化，怎么处理？

zookeeper在设置新的监视点之前会先去查询该节点数据，

### 客户端可以设置多种监视点

监控znode的数据变化

监控znode的子节点变化

监控znode的创建和删除

### 版本

每个znode上面都有一个递增的版本号，只有转入参数的版本号和服务器版本号一致调用才能成功。

## zookeeper架构

zookeeper服务端运行于两种模式下：独立模式和仲裁模式。

独立模式：有一个单独的服务器，zookeeper状态无法复制

仲裁模式：具有一组zookeeper服务器。

### zookeeper仲裁

过半的服务器完成命令就可以返回给客户端。奇数个服务器更加健壮。

### 会话

客户端初始化连接到一个服务器就会创建一个会话。

当一个会话因为某个原因中断，该会话期间创建的临时节点会消失

单个会话保证FIFO先进先出的顺序执行

## 安装zookeeper

下载zookeeper的包

解压tar -zxvf

修改conf下配置文件为zoo.cfg

修改data日志位置

进入bin启动zookeeper服务端

## 会话的状态和声明周期

![image-20200826160725901](img\zookeeper2.png)

会话创建时会设置一个会话超时时间的参数。

如果经过时间t之后服务接受不到任何会话消息，则会话超时。

经过t/3时间，就会向服务器发送心跳，经过2t/3时间没有回应就认为这个服务器挂了，还剩下t/3的时间去寻找其他服务器。

## 仲裁模式的配置

![image-20200826161803900](img\zookeeper3.png)

## 通过zookeeper实现锁

使用临时节点来创建锁，临时节点能防止此进程崩溃导致锁无法释放的问题。

进程监听/lock的状态，当监听到/lock删除的时候来创建锁。如果其他线程已经创建了则继续监听。

## 主-从模式

三个角色：

主节点：负责监听新的从节点和任务，分配任务给可用的从节点

从节点：从节点会向系统注册自己，以确保主节点看到他们执行任务，然后开始监听新任务。

客户端：创建任务并等待回应。

### 主节点

```
create -e /master "www.ss.com:2223"
创建临时节点 -e
```

此时其他线程在创建该节点会失败。

```
stat /master true
对主节点设置监听
```

监听到deleteNode事件。

在设置其他主节点就能成功

### 从节点，任务和分配

创建三个重要的父节点 /workers  ,/tasks  , /assign

这三个znode是持久节点，且不包含任何数据

主节点需要监控/works和/tasks子节点的变化情况

```
ls /works true
ls /tasks true
```

从节点需要先告诉主节点可以执行任务

从节点通过在/works下创建临时子节点通知

```
create -e /works/work1.example.com "worker1.example.com:2224"
因为之前主节点监听了/works节点，所以创建znode主节点就能察觉到
```

下一步从节点需要创建一个节点 /assign/worker1.example.com来接受任务分配，并通过第二个参数为true的ls来监听节点变化。

```
create /assign/worker1.example.com ""
ls /assign/worker1.example.com true
```

### 客户端角色

假设客户端请求主节点来运行cmd命令

```
create -s /tasks/task- "cmd"
```

我们需要按照任务添加顺序执行，本质上是一个队列

从节点执行完任务会创建一个znode来表示执行的状态，客户端查看znode是否创建来判断是否执行完成。

因此客户端需要监控znode的创建事件

```
ls /tasks/task-0000000000 true
```

一旦创建任务的znode，主节点会察觉到事件，主节点获取可用的从节点，分配任务。

![image-20200826170117500](img\zookeeper4.png)

![image-20200826170201510](img\zookeeper5.png)

一旦完成任务的执行，他就会在tasks中添加一个状态znode

![image-20200826170259802](img\zookeeper6.png)

之后客户端接收到通知，并查看结果