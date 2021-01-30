/*
 * @author yjiewei
 * @date 2021/1/30 13:38
 */
package com.yjiewei.asymmetric;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.*;

/**
 * RSA非对称加密算法
 * 随机生成公私密钥对
 */
public class RSADemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String algorithm = "RSA";

        // 根据算法创建生成密钥对对象
        KeyPairGenerator instance = KeyPairGenerator.getInstance(algorithm);

        // 对象生成密钥对
        KeyPair keyPair = instance.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 获取密钥字节数组
        byte[] privateKeyEncoded = privateKey.getEncoded();
        byte[] publicKeyEncoded = publicKey.getEncoded();

        // 对公私钥进行base64编码
        String privateKeyString = Base64.encode(privateKeyEncoded);
        String publicKeyString = Base64.encode(publicKeyEncoded);

        // 打印密钥字符串 超长
        System.out.println(privateKeyString);
        System.out.println(publicKeyString);
    }
}
