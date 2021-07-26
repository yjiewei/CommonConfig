package com.yjiewei;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.nio.charset.StandardCharsets;

/**
 * @author yjiewei
 * @date 2021/7/17
 */
public class ShiroMD5Test {
    public static void main(String[] args) {
        // 1.不地道的用法
        Md5Hash md5Hash = new Md5Hash();
        md5Hash.setBytes("123456".getBytes(StandardCharsets.UTF_8));
        String s = md5Hash.toHex();
        System.out.println(s);

        // 2.构造方法使用
        Md5Hash hash = new Md5Hash("123456");
        System.out.println(hash.toHex());

        // 3.加盐构造方法使用，通常是随机数值或者字符串
        Md5Hash hash1 = new Md5Hash("123456", "1234567890");
        System.out.println(hash1.toHex());

        // 4.md5+salt+hash
        Md5Hash hash2 = new Md5Hash("123456", "1234567890", 1024);
        System.out.println(hash2.toHex());

    }
}
