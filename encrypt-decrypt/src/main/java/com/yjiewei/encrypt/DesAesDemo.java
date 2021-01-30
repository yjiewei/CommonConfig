/*
 * @author yjiewei
 * @date 2021/1/29 10:59
 */
package com.yjiewei.encrypt;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *  现代加密：Des，Aes对称加密
 *  java.security.InvalidKeyException: Wrong key size
 *  String key = "123456"; 必须要是8位DES
 *
 *  负数，没有出现在 ascii 码表里面，所以出现乱码，需要配合base64进行转码
 *
 *  DES AES 两者代码没什么区别，但是AES是高级的，所以需要加密的密钥是16个字节
 *  String key = "1234567812345678";
 *
 */
public class DesAesDemo {
    public static void main(String[] args) throws Exception {
        // 原文
        String input = "杰炜";
        // des加密必须是8位
        // String key = "12345678";
        String key = "1234567812345678";
        // 算法
        // String algorithm = "DES";

        // String transformation = "DES";

        String algorithm = "AES";

        String transformation = "AES";
        // Cipher：密码，获取加密对象
        // transformation:参数表示使用什么类型加密
        Cipher cipher = Cipher.getInstance(transformation); // 返回一个 Cipher对象实现指定的变换

        // 指定秘钥规则
        // 第一个参数表示：密钥，key的字节数组
        // 第二个参数表示：算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm); // 从给定的字节数组构造一个密钥

        // 对加密进行初始化
        // 第一个参数：表示模式，有加密模式和解密模式
        // 第二个参数：表示秘钥规则
        cipher.init(Cipher.ENCRYPT_MODE,sks);

        // 进行加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        // 打印字节，因为ascii码有负数，解析不出来，所以乱码
//         for (byte b : bytes) {
//             System.out.println(b);
//         }

        // 打印密文 ascii码中有负数，解析会乱码，所以得用base64来解析
        String encode = Base64.encode(bytes); // TqK22mu/Srg=
        System.out.println(encode);
    }
}
