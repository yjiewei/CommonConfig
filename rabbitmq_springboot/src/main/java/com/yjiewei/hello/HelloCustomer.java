/*
 * @author yangjiewei
 * @date 2020/12/6 16:34
 */
package com.yjiewei.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue("hello")) // 监听hello队列，没有就创建一个，默认持久化以及不是自动删除等 true,false,false
public class HelloCustomer {

    @RabbitHandler // 消息处理
    public void receive1(String message){
        System.out.println("message:"+message);
    }
}
