package com.yjiewei.controller;

import com.yjiewei.entity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * @author yjiewei
 * @date 2021/7/3
 */
@RestController
@RequestMapping("/yjiewei")
public class HTTPClientController {

    @GetMapping
    public String httpGetWithArgs(String name, Integer age) {
        return "httpclient的get方法，传入参数的名字是 " + name + ",年龄是 " + age;
    }

    @PostMapping
    public String httpPostWithArgs(@RequestBody User user, String name, Integer age) {
        return "httpPostWithArgs，post方法，传入参数的名字是 " + name + ",年龄是 " + age + "-用户信息是：" + user;
    }

    // 对https进行验证的这一块内容需要回看其他demo

    @PostMapping("/filesUpload")
    public String httpclientFilesUpload(String name,
                                        Integer age,
                                        List<MultipartFile> files) throws IOException {
        StringBuilder sb = new StringBuilder(64);
        sb.append(name).append("-").append(age);
        System.out.println();
        String fileName;
        String newFileName;
        for (MultipartFile file : files) {
            sb.append("上传的文件信息: ");
            fileName = file.getOriginalFilename();
            if (fileName != null && !fileName.equals("")){
                String decode = URLDecoder.decode(fileName, "utf-8"); // 对文件名进行解码
                sb.append("\t文件名：").append(decode);
                sb.append("\t文件大小: ").append(file.getSize() * 1.0 / 1024).append("KB");
                sb.append("\tContentType: ").append(file.getContentType());
                sb.append("\n");
                newFileName = "_" + decode;
                file.transferTo(Paths.get(newFileName));
            }
        }
        return sb.toString();
    }

}
