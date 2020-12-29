SpringCloud微服务

## 服务注册原理

![image-20200909205907490](C:\Users\lihuakang\AppData\Roaming\Typora\typora-user-images\image-20200909205907490.png)

Eureka Server自我保护机制：触发条件，每分钟心跳数<n时触发自我保护，实例数*2* 续租百分比0.85=n

### 启动过程

1 读取和server的交互配置信息

2 读取自身的配置信息，封装

3 去server端拉取注册信息，并缓存到本地

4 服务注册

5 三个定时任务。

### 运行过程

1 定时发送心跳到server端，持续续约。

2 从server端拉取注册表信息，并更新本地缓存

3  监控自身变化，有变化再去注册

### 销毁过程

把server端自己的租约销毁掉



## 搭建服务注册中心

```
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka-server</artifactId>
        </dependency>
        <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Brixton.SR5</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

```

配置Eureka服务器注册中心

```
server.port=1111

eureka.instance.hostname=localhost
#作为服务器不需要向自己注册
eureka.client.register-with-eureka=false
#服务器不需要检索服务
eureka.client.fetch-registry=false
eureka.client.service-url.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/

```

启动类

```
@EnableEurekaServer
@SpringBootApplication
public class SpringcloudApplication {

    public static void main(String[] args) {

//        SpringApplication.run(SpringcloudApplication.class, args);
        new SpringApplicationBuilder(SpringcloudApplication.class).web(true).run(args);
    }

}

```



### 注册服务提供者

引入依赖

```
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka</artifactId>
		</dependency>
		<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>Brixton.SR5</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

```

application.properties

```
spring.application.name=hello-service
eureka.client.serviceUrl.defaultZone=http://localhost:1111/eureka/
```

启动类

```
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceproducttestApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServiceproducttestApplication.class, args);
    }
}
```

基础架构：
服务注册中心：提供服务注册与发现功能，也就是eureka-server
服务提供者：HELLO-SERVICE
服务消费者：消费者应用从服务注册中心获取服务列表，从而调用所需要的服务
服务治理机制：
服务注册中心-1和服务注册中心-2，特们互相注册组成了高可用集群
服务提供者启动了两个实例，一个注册到服务注册中心1上，另一个注册到服务注册中心2上
还有两个服务消费者，他们呢分别指向了一个注册中心

### **服务提供者**

服务提供者再启动的时候REST请求的方式将自己注册到EurekaServer上，将元数据存储在一个双层结构map上，第一层key是服务名，第二层key是服务的实例名，

### **服务同步**

由于服务注册中心之间因为是互相注册为服务，当服务提供者发送请求到一个注册中心的时候，他会将请求转发到集群中相连的其他注册中心，从而实现中心之间的服务同步。

### **服务续约**

读物提供者回维护一个心跳来高速Eureka Server还或者，防止EurekaServer的剔除任务，我们将该操作服务续约。
有两个重要属性：
eureka.instance.lease-renewal-interval-in-seconds=30
用于定义服务续约任务的调用时间间隔
eureka.instance.lease-expiration-duration-in-seconds=90
用于定义服务失效的时间，默认为90秒

### **服务消费者**

Eureka Server会维护一份只读的服务清单返回客户端，同时清单会每隔30s进行更新一次。
eureka.client.registry-fetch-inteerval-seconds=30修改清单更新时间
对于方法实例的选择，Eureka中有Region和Zone的概念，一个Region可以包含多个Zone,每个客户端需要被注册到一个Zone中，所以客户端对应一个Region和一个Zone

### **服务下线**

当服务非正常关闭的时候，会触发服务下线的请求给Eureka Server告诉服务注册中心我要下线了。

### **失效剔除**

Eureka Server再启动的时候会创建一个定时任务，默认每个一段时间（默认60秒）将当前清单中超时（默认90秒）的没有续约的服务剔除出去。

### **自我保护**

服务注册到Eureka Server 会维护一个心跳连接。服务器必须有容错机制，比如请求重试、断路器。
eureka.server.enable-self-preservation=false参数关闭保护机制

### **原理分析**

Eureka有服务注册中心，服务提供者，服务消费者三个元素，后两者属于Eureka客户端。
我们将一个springboot应用注册到Eureka Server中主要做两件事
1.再主类中配置了@EnableDiscoveryClient注解
2再application.properties中用eureka.client.serviceUrl.defaultZone指定服务注册中心的位置。
DiscoveryClient类与Eureka Server相互协作
Eureka Client负责下面的任务
-向Eureka Server注册服务实例
-向Eureka Server服务续约
-当服务关闭期间，向Eureka Server取消租约
-查询Eureka Server中的服务实例列表
Eureka Client需要配置一个Eureka Server的URL列表
通过配置属性eureka.client.serviceUrl.defaultZone
**Region 、Zone**
客户端加载两个内容，第一个是Region第二个是Zone。一个Region对应多个Zone，再获取region和Zone信息之后，才真正开始加载Eureka Server的具体地址。
eureka.client.serviceUrl.defaultZone属性可以配置多个，Ribbon默认策略会优先访问同客户端下的一个Zone中的实例。

### **服务获取与服务续约**

虚无注册到Eureka Server后，子然后需要一个心跳去续约，方法被剔除
参数：

```java
eureka.instance.lease-renewal-interval-in-seconds=30每隔30秒续约一次
	eureka.instance.lease-expiration-duration-in-seconds=90 90秒不更新被剔除
12
eureka.client.fetch-registry=true定期更新服务清单，默认开启
1
```

Eureka客户端的配置只要分成两个方面
服务注册相关的配置：保活注册中心的地址，服务获取的时间间隔，可用区域等
服务实例相关的配置信息包括服务实例的名称，ip地址，端口号，健康路径检查。
**端点配置**
springcloud Eureka中默认使用了springboot-actuator模块提供的/info和/health端点

```java
management.context-path=/hello
eureka.instance.statusPageUrlPath=${management.context-path}/info
eureka.instance.healthCheckUrlPath=${management.context-path}/health
```

#### 自我保护触发

**客户端每分钟续约数量小于客户端总数的85%时会触发保护机制**

服务实例数：10个，期望每分钟续约数：10 * 2=20，期望阈值：20*0.85=17，自我保护少于17时 触发。

## 安全配置

### 开启Eureka安全连接

```
spring.security.user.name=admin
spring.security.user.password=123
```

### 手动关闭 防止跨域攻击

```
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable();
		super.configure(http);
	}

}

```

