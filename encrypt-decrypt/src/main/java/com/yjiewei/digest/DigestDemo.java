/*
 * @author yjiewei
 * @date 2021/1/30 12:39
 */
package com.yjiewei.digest;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 哈希值：43599366cb050c9aa697f77f1fdd43fa  32位=16*2
// base64解码后：Q1mTZssFDJqml/d/H91D+g==
public class DigestDemo {
    public static void main(String[] args) throws Exception {
        String input = "杨杰炜";
        String algorithm = "MD5";
        String digest = getDigest(input, algorithm);
        System.out.println(digest);

        String sha1 = getDigest(input, "SHA-1");
        System.out.println("SHA1: "+sha1);

        String sha256 = getDigest(input, "SHA-256");
        System.out.println("SHA256: "+sha256);

        String sha512 = getDigest(input, "SHA-512");
        System.out.println("SHA512: "+sha512);
    }

    /**
     * 根据密码和加密算法进行加密
     * @param input     : 原文
     * @param algorithm : 加密算法
     * @return
     * @throws Exception
     */
    public static String getDigest(String input, String algorithm) throws Exception {
        MessageDigest instance = MessageDigest.getInstance(algorithm);
        byte[] digest = instance.digest(input.getBytes());
        // String encode = Base64.encode(digest);
        // System.out.println(encode); // Q1mTZssFDJqml/d/H91D+g==

        return toHex(digest);
    }

    /**
     * base64编码转16进制
     * @param digest : base64编码字节数组
     * @return
     */
    public static String toHex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        // base64编码转成16进制
        for (byte b : digest) {
            String s = Integer.toHexString(b & 0xff); // 按位与，转换为16进制
            // 0-63，在16以内的要加一个0在前面保证位数，为什么要是2位
            if(s.length() == 1){
                s = "0" + s; // 只有一个字符时要加个0
            }
            sb.append(s);
        }
        return sb.toString();
    }
}
