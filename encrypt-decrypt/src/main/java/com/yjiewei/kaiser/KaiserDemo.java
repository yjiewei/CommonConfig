/*
 * @author yjiewei
 * @date 2021/1/29 9:38
 */
package com.yjiewei.kaiser;


/**
 * 凯撒加密与解密：就是位移加密法
 */
public class KaiserDemo {
    public static void main(String[] args) {
        String original = "hello world";
        int key = 3;
        String encrypt = kaiserEncrypt(original, key);
        System.out.println("加密：" + encrypt);
        String decrypt = kaiserDecrypt(encrypt, key);
        System.out.println("解密：" + decrypt);
    }

    private static String kaiserDecrypt(String encrypt, int key) {
        StringBuilder sb = new StringBuilder();
        char[] chars = encrypt.toCharArray();
        for (char c : chars) {
            int asciiCode = c;
            asciiCode = asciiCode - key;
            char newChar = (char) asciiCode;
            sb.append(newChar);
        }
        return sb.toString();
    }

    public static String kaiserEncrypt(String original, int key) {
        StringBuilder sb = new StringBuilder();
        char[] chars = original.toCharArray();
        for (char c : chars) {
            int asciiCode = c;
            asciiCode = asciiCode + key;
            char newChar = (char)asciiCode;
            sb.append(newChar);
        }
        return sb.toString();
    }
}
