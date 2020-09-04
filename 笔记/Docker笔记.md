Docker笔记

## 在centos7上安装Docker

```
[root@localhost ~]# yum update
已加载插件：fastestmirror
Loading mirror speeds from cached hostfile
 * base: centos.ustc.edu.cn
 * extras: mirrors.aliyun.com
 * updates: mirrors.cn99.com
base                                                                                                  | 3.6 kB  00:00:00     
extras                                                                                                | 3.4 kB  00:00:00     
updates                                                                                               | 3.4 kB  00:00:00     
正在解决依赖关系
--> 正在检查事务
---> 软件包 NetworkManager.x86_64.1.1.12.0-6.el7 将被 升级
---> 软件包 NetworkManager.x86_64.1.1.12.0-10.el7_6 将被 更新
```

```
[root@localhost ~]# yum install -y yum-utils device-mapper-persistent-data lvm2
已加载插件：fastestmirror
Loading mirror speeds from cached hostfile
 * base: centos.ustc.edu.cn
 * extras: mirrors.aliyun.com
 * updates: mirrors.cn99.com
软件包 device-mapper-persistent-data-0.7.3-3.el7.x86_64 已安装并且是最新版本
软件包 7:lvm2-2.02.180-10.el7_6.8.x86_64 已安装并且是最新版本
正在解决依赖关系
--> 正在检查事务
---> 软件包 yum-utils.noarch.0.1.1.31-50.el7 将被 安装
--> 正在处理依赖关系 python-kitchen，它被软件包 yum-utils-1.1.31-50.el7.noarch 需要
...
...
```

设置yum源（选择其中一个）

yum-config-manager --add-repo http://download.docker.com/linux/centos/docker-ce.repo（中央仓库）

yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo（阿里仓库）

```
[root@localhost ~]# yum-config-manager --add-repo 
https://download.docker.com/linux/centos/docker-ce.repo
已加载插件：fastestmirror
adding repo from: https://download.docker.com/linux/centos/docker-ce.repo
grabbing file https://download.docker.com/linux/centos/docker-ce.repo to 
/etc/yum.repos.d/docker-ce.repo
repo saved to /etc/yum.repos.d/docker-ce.repo
```

安装Docker，命令：yum install docker-ce-版本号，我选的是docker-ce-18.03.1.ce

```
[root@localhost ~]# yum install docker-ce-18.03.1.ce
已加载插件：fastestmirror
Loading mirror speeds from cached hostfile
 * base: centos.ustc.edu.cn
 * extras: mirrors.aliyun.com
 * updates: mirrors.cn99.com
正在解决依赖关系
--> 正在检查事务
---> 软件包 docker-ce.x86_64.0.18.03.1.ce-1.el7.centos 将被 安装
```

启动Docker，命令：systemctl start docker，然后加入开机启动

```
[root@localhost ~]# systemctl start docker
[root@localhost ~]# systemctl enable  docker
Created symlink from /etc/systemd/system/multi-user.target.wants/docker.service to /usr/lib/systemd/system/docker.service.
[root@localhost ~]# docker version
Client:
 Version:      18.03.1-ce
 API version:  1.37
 Go version:   go1.9.5
 Git commit:   9ee9f40
 Built:        Thu Apr 26 07:20:16 2018
 OS/Arch:      linux/amd64
 Experimental: false
 Orchestrator: swarm
 
Server:
 Engine:
  Version:      18.03.1-ce
  API version:  1.37 (minimum version 1.12)
  Go version:   go1.9.5
  Git commit:   9ee9f40
  Built:        Thu Apr 26 07:23:58 2018
  OS/Arch:      linux/amd64
  Experimental: false
```

## Docker镜像

docker pull  从仓库拉取镜像

docker run   -t -i使用镜像创建容器,会产生一个容器id

docker images  列出本地所有镜像

docker commit 提交更新后的副本，-a 指定更新的用户信息

docker build 创建镜像

docker tag 修改镜像标签

docker push 上传镜像到中央仓库

docker save -o xxx.tar xxx  导出镜像到本地

docker load --input xxx.tar 导入到本地镜像库

docker rmi 移除本地镜像

### 通过Dockerfile构建镜像

创建dockerfile文件

touch DockerFile

```
from ubuntu:14.04
MAINTAINER Docker Newbee <newbee@docker.com>
RUN apt-get -qq update
# 注释
```

from是告诉docker以哪个镜像为基础

RUN是在创建中运行

```
docker build -t "train:v2" .
#镜像名称为train:v2就是tag
#   .  是dockerfile所在目录，就是当前目录
```

还可以利用 	ADD	命令复制本地文件到镜像；用 	EXPOSE	命令来向外部开放端口；用 	CMD	命令来描述容器启动后运行的程序等。  

## Docker容器

简单的说，容器是独立运行的一个或一组应用，以及它们的运行态环境。 对应的，虚拟机可以理解为模拟运行的一整套操作系统（提供了运行态环境和其他系统环境）和跑在上面的应用  。

### 启动容器

docker run 

```
 docker run ubuntu:14.04 /bin/echo 'HelloWorld'
 打印HelloWorld 然后关闭容器
```

```
sudo	docker	run	-t	-i	ubuntu:14.04	/bin/bash
启动一个bash客户端，并允许和用户交互
```

可以利用 	docker	start	命令，直接将一个已经终止的容器启动运行。  

需要让	Docker	容器在后台以守护态（Daemonized）形式运行。此时，可以通过添加 	-d	参数来实现。  

docker ps查看允许的容器

docker logs 容器名   查看容器打印 日志

docker stop 终止容器

exit或者Ctrl+d退出客户端

docker restart 重启容器

docker run -idt ubuntu后台运行ubuntu

docker attach 容器名称              进入容器交互

ctrl+P+Q   退出交互页面，不退出容器

### 导出容器

docker ps -a 查看容器进程

docker export  容器ID>xxx.tar

### 导入容器快照

cat xxx.tar | import - test/ubuntu:v1.0

docker import xxx.tar   xx:v2.0

### 删除容器

docker rm 容器名称

## 仓库

### Docker Hub

官方仓库 Docker Hub

docker login 登录信息

本地用户信息在.dockercfg中保存

docker search 查找仓库的镜像

docker pull 拉取镜像

docker push 推送镜像

### 私有仓库

docker-registry

## Docker数据管理

数据卷（Data volumes）

数据卷容器(Data volumes containers)

### 数据卷

数据卷是一个供一个或者多个容器使用的特殊目录。数据卷可以在多个容器之间共享和重用。

#### 创建数据卷

docekr run 的时候 -v 数据卷目录。  来创建一个数据卷并挂载到容器里。一次run可以挂载多个数据卷。

```
docker run -d -P --name web -v /opt training/webapp
```

可以在dockerfile中使用volume 来创建一个数据卷用于该镜像创建的所有容器。

```
docker run -it -v /宿主机绝对目录：/容器内目录 镜像名
docker run -it -v /myDataVolume:/dataVolumeContainer centos
```

只读

```
docker run -it -v /宿主机绝对路径：/容器内目录：ro镜像名
ro是指readonly
```

查看数据是否挂载成功？docker inspect 容器ID

### 数据卷容器

数据卷容器就是一个容器，专门用来提供数据卷供其他容器挂载

创建一个数据卷容器

```
docker  run -d  -v /dbdata --name dbdata
```

其他容器使用--volumes-from 来挂载dbdata容器中的数据卷

```
docker run -d --volumes-from dbdata --name db1 training/postgres
```

docker rm -v 同时删除关联的容器

### 利用数据据容器来备份和恢复

备份：

使用 --volumes-from 来创建一个加载dbdata的容器卷的容器

使用tar  备份

恢复：

使用docker run --volumes-from dbdata -v $(pwd):/backup busy tar zxvf xxx.tar

## Docker 的网络功能

-p  指定端口映射

docker logs 查看日志

```
docker run -d -p  5000:5000 ubuntu

映射本地5000端口到容器端口5000
```

docker port 容器名 5000

docker run --name 自定义容器名

### 容器互联

容器互联是除了端口映射外另一种跟容器交互的方式。

docker run -d -P --name web --link db:db training/webapp python app.py

--link 参数格式 --link name:alias   其中name是要连接的容器，alias是这个连接的别名。

 docker inspect 746eecf52617 查看容器信息

## 高级网络配置

docker启动的时候，会自动创建一个docekr0虚拟网桥，Docker	随机分配一个本地未占用的私有网段（在 RFC1918 中定义）中的一个地址给 	docker0	接
口。比如典型的 	172.17.42.1	，掩码为 	255.255.0.0	。此后启动的容器内的网口也会自动分配一个同一网段（	172.17.0.0/16	）的地址  。



当创建一个	Docker	容器的时候，同时会创建了一对 	veth	pair	接口（当数据包发送到一个接口时， 另外一个接口也可以收到相同的数据包）。 这对接口一端在容器内，即 	eth0	； 另一端在本地并被挂载到docker0	网桥，名称以 	veth	开头（例如 	vethAQI2QT	）。通过这种方式，主机可以跟容器通信，容器之间也可以相互通信。 Docker	就创建了在主机和所有容器之间一个虚拟共享网络。  

![image-20200903165225439](img\docker1.png)

### 快速配置

下面是一个跟	Docker	网络相关的命令列表。
其中有些命令选项只有在	Docker	服务启动的时候才能配置，而且不能马上生效。
	-b	BRIDGE	or	--bridge=BRIDGE		--指定容器挂载的网桥
	--bip=CIDR		--定制	docker0	的掩码
	-H	SOCKET...	or	--host=SOCKET...		--Docker	服务端接收命令的通道
	--icc=true|false		--是否支持容器之间进行通信
	--ip-forward=true|false		--请看下文容器之间的通信
	--iptables=true|false		--禁止	Docker	添加	iptables	规则
	--mtu=BYTES		--容器网络中的	MTU
下面2个命令选项既可以在启动服务时指定，也可以	Docker	容器启动（	docker	run	） 时候指定。在
Docker	服务启动的时候指定则会成为默认值，后面执行 	docker	run	时可以覆盖设置的默认值。
	--dns=IP_ADDRESS...		--使用指定的DNS服务器
	--dns-search=DOMAIN...		--指定DNS搜索域
最后这些选项只有在 	docker	run	执行时使用，因为它是针对容器的特性内容。
	-h	HOSTNAME	or	--hostname=HOSTNAME		--配置容器主机名
	--link=CONTAINER_NAME:ALIAS		--添加到另一个容器的连接
	--net=bridge|none|container:NAME_or_ID|host		--配置容器的桥接模式
	-p	SPEC	or	--publish=SPEC		--映射容器端口到宿主主机
	-P	or	--publish-all=true|false		--映射容器所有端口到宿主主机  

### 配置DNS

Docker	没有为每个容器专门定制镜像，那么怎么自定义配置容器的主机名和	DNS	配置呢？ 秘诀就是它利用虚拟文件来挂载到来容器的	3	个相关配置文件。  

### 容器访问控制

主要通过linux系统自带的iptables防火墙来进行管理

#### 容器访问外部网络

容器想要访问外部网络，必须有本地系统的转发的支持。

```
sysctl net.ipv4.ip_forward
如果为1则为开启，为0则未开启
```

```
sysctl  -w  net.ipv4.ip_forward=1
手动设置转发支持
```

在启动容器的时候，加上参数--ip-forward=true ,Docker就会自动指定系统的ip_forward参数为1

### 容器之间访问

容器之间访问需要两方面的支持，默认所有容器都会被挂载到docker0网桥上，

本地防火墙是否允许访问，--iptables是否允许通过。



启动docker容器时候，默认是可以相互访问的，可以使用参数--icc=false则不会访问。



#### 访问指定端口

-icc=false关闭之后，可以通过--link=container:alias选项来访问容器的开放端口。

## 映射容器端口到宿主主机

默认情况，容器可以主动访问到外部网络的连接，但是外部网络无法访问到容器。

### 容器访问外部实现

容器所有才外部的连接，都会被NAT变成本地的ip地址。

### 外部访问容器的实现

可以在docker run 的时候加参数-P 或者-p

## 配置docker0网桥

网桥可以使真机和虚拟机之间直接交换数据。

可以在服务启动时配置参数，

--bip=CIDR --IP地址加子网掩码格式

--mtu=BYTES  --最大传输单位



也可以通过配置文件修改。

```
sudo brctl show
# 查看网桥信息
```

每次新建容器的时候，Docker都会从可用网段中选择一个空闲的ip地址给容器的eth0

### 自定义网桥

启动docker服务的时候，使用-b BRIDGE或者--bridge=BRIDGE 来指定使用的网桥。

```
停止服务，删除旧的网桥
service docker stop
ip link set dev docker0 down
brctl delbr docker0
```

创建一个网桥bridge0

```
brctl addbr bridge0
ip addr add 192.168.5.1/24 dev bridge0
ip link set dev bridge0 up
```

ip addr show bridge0

## Dockerfile

指令：

From<image>  必须是第一条指令，在一个dockerfile中创建多个镜像时可以使用多个from。

MAINTAINER<name>  指定维护者信息

RUN：在当前镜像上执行的指令，创建一层新的镜像。

CMD：支持三种格式

​	CMD["executable","param1","param2"]使用exec执行

​	CMD command param1 param2 在/bin/bash中执行，提供给需要交互的应用

​	CMD ["param1","param2"]提供给ENTRYPOINT的默认参数

每个Dockerfile只能执行写一条CMD，如果有多条只会执行最后一条。

EXPORT <port>  告诉容器暴露的端口号，也可以在容器启动的时候-P系统会自动分配一个端口号。

ENV <key><value> 指定一个环境变量，后续提供给RUN使用

ADD<src><dest>  该命令将src目录复制到容器的value目录。src可以是dockerfile的相对目录

COPY<src><value>复制主机的src到容器的dest

ENTRYPOINT ["executable","param1","param2"]  在容器启动后执行，不会被docker run提供的参数覆盖。dockerfile中只能执行一条ENTRYPOINT，多条指挥执行最后一条。

VOLUME ["/data"]创建一个可以从本地主机或者其他容器挂载的挂载点，一般用来存放数据库。

USER deamon 指定运行容器的用户后者UID

WORKDIR  /path/to/workdir  为后续的RUN、CMD、ENTRYPOINT指定配置的工作目录。

ONBUILD：配置当所创建的镜像作为其他新创建的镜像时所执行的命令。

### 创建镜像

docker build [选项] 路径

