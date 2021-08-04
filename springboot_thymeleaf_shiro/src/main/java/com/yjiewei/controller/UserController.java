package com.yjiewei.controller;

import com.yjiewei.entity.User;
import com.yjiewei.service.UserService;
import com.yjiewei.util.VerifyCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author yjiewei
 * @date 2021/7/18
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("loginView")
    public String loginView(){
        System.out.println("loginView页面跳转");
        return "login";
    }

    @RequestMapping("registerView")
    public String registerView(){
        System.out.println("registerView页面跳转");
        return "register";
    }

    /**
     * 用户身份验证
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login")
    public String login(String username, String password, String code, HttpSession session){
        // 1.获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //比较验证码
        String codes = (String) session.getAttribute("code");
        try{
            if (codes.equals(code)) {
                subject.login(new UsernamePasswordToken(username, password));
                return "index";
            }else {
                throw new RuntimeException("验证码错误");
            }
        }catch (UnknownAccountException e){
            System.out.println("用户名错误");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
        }
        return "login";
    }

    /**
     * 退出登录
     * @return 重定向到登录界面
     */
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("register")
    public String register(User user){
        try{
            userService.register(user);
            return "login";
        }catch (Exception e) {
            e.printStackTrace();
            return "register";
        }
    }

    /**
     * 模拟保存方法
     * 实际上知识为了测试页面访问URL时是否有权限访问
     * @return
     */
    @RequestMapping("save")
    public String save(){
        System.out.println("进入方法");

        //1.基于角色
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //代码方式
        if (subject.hasRole("admin")) {
            System.out.println("保存订单!");
        }else{
            System.out.println("无权访问!");
        }
        //2.基于权限字符串
        // ...
        return "index";
    }

    @RequestMapping("getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        //1.生成验证码
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //2.验证码放入session
        session.setAttribute("code", verifyCode);
        //3.验证码放入图片
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("image/png");
        VerifyCodeUtils.outputImage(200, 60, outputStream, verifyCode);
    }
}
