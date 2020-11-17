/*
 * @author yangjiewei
 * @date 2020/11/12 16:16
 */
package com.yjiewei.controller;

import com.yjiewei.entity.Account;
import com.yjiewei.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        System.out.println("这里模拟控制层跳转到list.jsp页面，路径已经在视图解析器配置好了");
        List<Account> all = accountService.findAll();
        model.addAttribute("all",all);
        return "list";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save() throws Exception {
        // System.out.println("这里模拟控制层跳转到list.jsp页面，路径已经在视图解析器配置好了");
        accountService.saveAccount(new Account("yjiewei", 12000D));
        return "保存成功！！";
    }
}
