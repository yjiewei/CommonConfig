/*
 * @author yangjiewei
 * @date 2020/12/6 16:43
 */
package com.yjiewei.workqueue;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkCustomer {

    // 监听器如果放在方法上就不需要用handler注解了，注意这里不一定是string类型，根据业务场景改变的
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void reveive1(String message){
        System.out.println("message1:"+message);
    }

    // 监听器如果放在方法上就不需要用handler注解了，注意这里不一定是string类型，根据业务场景改变的
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void reveive2(String message){
        System.out.println("message2:"+message);
    }
}
