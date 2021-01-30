/*
 * @author yjiewei
 * @date 2021/1/30 13:48
 */
package com.yjiewei.asymmetric;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

/**
 * 用RSA非对称私钥来加密 杨杰炜 这个密码
 * CjGs1yUOAMtfmjRz63T4dg7/VGlFywZTVoX6uElUH4vEo0Of5tS09A7FH3Q3zr92miP1wRi1vo90V
 * wBwYF0OcGvt+ChGgCB2fhsUDY+4rqV5q3K9Vt0wK9cRAshlsAud40q7WA5HfBOB5NVWUFnDdlQ7IP
 * 6YTNdm5CFvHLgUSBg3vLHMu5lj+5Yofsu1iVPQMKJUFXu4WRl55JwVCxuaH9YFhsOdhAe+ScOvHxj
 * YTNXXJUB9m15Ni5nJaKgWZm3frDSgKn46WI5uM0W+pKdM+f+ig3FNpL1Y7a75bfGVJnC2Je3W1XiE
 * m82XyadUkIt7QIT4d6QDd0nlB88yJo8DKQ==
 */
public class RSAEncryptDecrypt {
    public static void main(String[] args) throws Exception{
        String input = "杨杰炜"; // 待会要加密的内容
        // 加密算法
        String algorithm = "RSA";
        //  创建密钥对生成器对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 生成公钥
        PublicKey publicKey = keyPair.getPublic();
        // 获取私钥字节数组
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 获取公钥字节数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        // 对公私钥进行base64编码
        String privateKeyString = Base64.encode(privateKeyEncoded);
        String publicKeyString = Base64.encode(publicKeyEncoded);

        // 用私钥加密
        byte[] bytes = encryptByPrivateKey(input, algorithm, privateKey);
        // 用base64编码
        System.out.println(Base64.encode(bytes));

        // 用公钥解密
        byte[] bytes1 = decryptByPublicKey(algorithm, publicKey, bytes);
        System.out.println(new String(bytes1));
    }

    public static byte[] decryptByPublicKey(String algorithm, PublicKey publicKey, byte[] bytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 创建加密对象
        // 参数表示加密算法
        Cipher cipher = Cipher.getInstance(algorithm);
        // 公钥进行解密
        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        // 对密文进行解密，不需要使用base64，因为原文不会乱码
        return cipher.doFinal(bytes);
    }

    public static byte[] encryptByPrivateKey(String input, String algorithm, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 创建加密对象
        // 参数表示加密算法
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化加密
        // 第一个参数:加密的模式
        // 第二个参数：使用私钥进行加密
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        // 私钥加密
        return cipher.doFinal(input.getBytes());
    }
}
