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

