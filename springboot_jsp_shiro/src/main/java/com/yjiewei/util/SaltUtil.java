package com.yjiewei.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author yjiewei
 * @date 2021/7/18
 */
public class SaltUtil {

    /**
     * 生成salt的静态方法
     * @param n
     * @return
     */
    public static String getSalt(int n) {
        StringBuilder sb = new StringBuilder();
        char[] chars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm7894561230!@#$%^&*()".toCharArray();
        for (int i = 0; i < n; i++) {
            char c = chars[new Random().nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
