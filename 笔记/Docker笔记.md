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

docker run -d -P --name web --link db:db training/webapp python app.py

--link 参数格式 --link name:alias   其中name是要连接的容器，alias是这个连接的别名。

