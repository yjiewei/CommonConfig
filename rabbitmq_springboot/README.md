### springboot整合使用rabbitmq以及常用API调用接口。

#### 使用的环境和rabbitmq安装包包括以下几个

- JDK1.8
- springboot 2.4.0
- socat-1.7.3.2-2.el7.x86_64.rpm
- rabbitmq-server-3.8.9-1.el7.noarch.rpm
- erlang-23.1.4-1.el7.x86_64.rpm

#### 导入的依赖主要有

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

```xml
<dependency>
    <groupId>org.springframework.amqp</groupId>
    <artifactId>spring-rabbit-test</artifactId>
    <scope>test</scope>
</dependency>
```



