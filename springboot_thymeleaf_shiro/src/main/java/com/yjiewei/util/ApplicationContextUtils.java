package com.yjiewei.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContext工具类
 * @author yjiewei
 * @date 2021/7/21
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override  // 这里根据注入情况添加
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    // 根据bean的名字获取容器中的bean
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }
}
