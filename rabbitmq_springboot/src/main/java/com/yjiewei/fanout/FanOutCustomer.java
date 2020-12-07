/*
 * @author yangjiewei
 * @date 2020/12/6 17:00
 */
package com.yjiewei.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanOutCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,  // 不指定名称说明创建的是临时队列
                    exchange = @Exchange(value = "logs",type = "fanout")  // 交换机的属性
            )
    })
    public void receive1(String message){
        System.out.println("message fanout 1 :"+ message);
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,  // 不指定名称说明创建的是临时队列
                    exchange = @Exchange(value = "logs",type = "fanout")  // 交换机的属性
            )
    })
    public void receive2(String message){
        System.out.println("message fanout 2 :"+ message);
    }
}
