# Docker

## 安装

```
yum update

yum install -y yum-utils device-mapper-persistent-data lvm2

yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum -y install docker-ce
```

#### 启动docker

```
service docker start
```

**查看版本**

```
docker version
//会安装客户端和服务端
```

### 配置阿里云镜像加速服务

登录阿里云，找到镜像加速器，复制命令。



## Docker基本概念

docker客户端通过restAPI调用Docker的服务端。

**镜像**：镜像是文件，只读的。提供运行程序完整的软硬件资源。

**容器**：是镜像的实例，由docker负责创建，容器之间相互隔离。



### docker执行过程

![image-20210201152413004](\img\docker-1.png)

docker pull 从远处registry拉取镜像到本地

docker run 创建容器并启动。



## 快速部署tomcat

docker pull 镜像名 <:tags>   从远程抽取镜像

docker images    查看本地镜像

docker run 镜像名 <:tags>    创建容器，启动应用。

docker ps    查看正在运行的容器进程

docker rm <-f> 容器id                删除容器

docker rmi <-f> 镜像名 <:tags>    删除镜像



```
docker pull tomcat     下载tomcat最新版本
docker pull tomcat:8.5.46-jdk8-openjdk   下载8.5
docker images   查看镜像
docker run tomcat
```

### Docker宿主机和容器通信

容器和宿主机做端口映射

```
docker run -p 8000:8080 tomcat
宿主机端口8000映射容器端口8080
```

不阻塞启动tomcat

```
docker run -p 8000:8080 -d tomcat
-d代表后台运行
```

停止tomcat

```
docker ps 查看运行的容器
docker stop 容器id
docker rm 容器id
```

![image-20210202103252808](\img\docker-2.png)

```
docker rm -f 容器id               //强制移除
docker images 镜像名:<tags>          //移除镜像
```

### tomcat容器内部结构

包含tomcat,jdk,linux

### 在容器中执行命令

docker exec  [-it]  容器id   命令

exec： 对应容器中执行命令

-it采用交换方式执行命令

```
docker exec -it a844e93ee627 /bin/bash
进入tomcat容器中交互，可修改配置文件等
```

### 宿主机存储容器和镜像的位置

/var/lib/docker

## 容器生命周期

```
docker create 容器名称           创建但不启动
docker ps -a
docker start 容器id               启动
docker pause 容器id               暂停
docker unpause 容器id
docker stop 容器id                 停止容器
docker rm -f 容器id               删除容器
```

## Dockerfile

dockerfile是一个包含用于组合镜像的命令的文本文档

docker通过读取dockerfile中指令按步执行，自动生成镜像

构建镜像

```
docker build -t 机构/镜像名<:tags> Dockerfile目录
```

dockerfile文件

```
FROM tomcat:latest      基于哪个镜像
MAINTAINER lihuakang.com  作者
WORKDIR /usr/local/tomcat/webapps  进入目录
ADD docker-web ./docker-web 指定文件或者目录复制到容器指定目录
```

在linux环境下，创建dockerfile文件，创建docker-web目录。

![image-20210202173045831](\img\docker-3.png)

创建运行容器

```
docker run -d -p 8001:8080 lihuakang/mywebapp:1.0
```



## 镜像分层

镜像文件只读。

通过镜像创建的容器可读可写。

容器与容器之间相互隔离。

dockerfile没执行一条命令就会给临时容器拍一个快照。

dockerfile在执行过程中，如果有镜像有这个命令就会Using cache ，执行新命令才会创建临时容器快照、

### Dockerfile中基础指令

FROM  centos     基于基准镜像

FROM scratch    不依赖于任何基准镜像

MAINTAINER lihuakang    作者

LABEL version='1.0'     描述信息

WORKDIR  /usr/local   相当于cd命令，没有自动创建

ADD  aa /web/aa    复制命令

ENV  JAVA_HOME /usr/local/openjdk8 设置环境常量

RUN ${JAVA_HOME}/bin/java -jar test.jar 运行指令

#### dockerfile执行命令

**RUN**： 在build构建时执行命令。

```
RUN yum install -y vim  #shell命令
RUN ["yum","install","-y","vim"] #Exec命令
```

shell执行时，当前shell是父进程，生成一个子进程，在子进程中执行shell脚本，执行完成退出子进程shell，回到当前shell。

Exec方式，会用exec进行替换原来进程，保持pid不变。

**ENTRYPOINT**:容器启动时执行命令。

```
ENTRYPOINT ["ps"]      
在dockerfile中只有最后一条ENTRYPOINT指令会执行。
```

**CMD**：容器启动后执行默认的命令或参数。

```
CMD ["ps","-ef"] 
CMD命令只有最后一条指令执行，如果容器启动时附加指令，则被CMD忽略、
```

![image-20210204150040177](\img\docker-4.png)

```
docker run lihuakang/run ls
这个ls会取代CMD命令
```

