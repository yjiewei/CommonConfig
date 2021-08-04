package com.yjiewei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yjiewei
 * @date 2021/8/4
 */
//@Controller // 这里可不能是@restcontroller
//@RequestMapping("user")
public class HelloController {

    @RequestMapping("login")
    public String hello(){
        return "login";
    }
}
