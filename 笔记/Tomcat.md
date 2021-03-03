# Tomcat

### Tomcat生命周期

init：初始化

start：启动容器

stop：停止容器

destroy:销毁组件

## Tomcat组件说明

### Server

表示整个servlet容器，因此tomcat运行环境只有唯一一个实例。

### Service

service表示一个或者多个Connector集合，这些Connector共享一个Container来处理请求。tomcat中可以包含多个service实例，他们彼此独立

### Connector

tomcat链接器，用于监听并转换Socket请求，

### Container

表示执行客户端请求并返回请求的一类对象，在Tomcat中存在不同级别的容器：Engine，Host，Context，Wrapper

### Engine

表示整个servlet引擎，Engine是最高级别的容器，Engine不直接处理请求，是获取目标容器的入口

### Host

host作为一类，Engine中的虚拟机，与域名有关，客户端可以通过域名访问服务器。

### Context

用于表示ServletContext，一个servletContext就是一个独立的web应用

### Wrapper

Wrapper作为一类容器，用于表示web应用中定义的Servlet

### Executor

表示tomcat组件可以共享线程池

## Catalina

Tomcat的servlet容器实现--Catalina

![image-20210301165341502](img\tomcat1.png)

### Digester

Digester是一款将Xml转换成Java对象的事件驱动型工具。

Digester是线程不安全的。

#### 对象栈

Digester对象栈：

clear清空栈

peek得到栈顶第n个元素，但是不移除

pop将位于栈顶的元素移除

push将对象放在栈顶



Digester自动遍历xml文档

