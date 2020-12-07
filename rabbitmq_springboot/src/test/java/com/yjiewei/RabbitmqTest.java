/*
 * @author yangjiewei
 * @date 2020/12/6 16:27
 */
package com.yjiewei;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// springboot 的测试类
// spring发布的一个工厂
@SpringBootTest(classes = RabbitmqSpringbootApplication.class)
@RunWith(SpringRunner.class)
public class RabbitmqTest {
    // 这里有一个模板对象可以使用
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test(){
        rabbitTemplate.convertAndSend("hello","hello yjiewei"); // 信息发送到hello队列中了
    }

    @Test
    public void testWork(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","hello this is work queue");
        }
    }

    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("logs","","fanout的模型发送的消息");
    }

    @Test // route key 比较固定，不灵活，所以会使用下面一种方法
    public void testRoute(){
        rabbitTemplate.convertAndSend("directs","info","这是发送给路由keyinfo的信息，交换机是directs");
    }

    // topic 订阅模式，动态路由
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topics","user.emmmm.yjiewei","routingkey是user.emmmm.yjiewei，动态路由信息");
    }
}
