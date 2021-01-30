/*
 * @author yjiewei
 * @date 2021/1/29 10:44
 */
package com.yjiewei.bytebit;

import java.io.UnsupportedEncodingException;

/**
 * 中文在UTF-8编码下, 占据3个字节
 * -26   11111111111111111111111111100110
 * -99   11111111111111111111111110011101
 * -88   11111111111111111111111110101000
 *
 * 中文在GBK编码下, 占据2个字节
 * -47   11111111111111111111111111010001
 * -18   11111111111111111111111111101110
 */
public class ChineseByteBit {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String a = "杨";
        // byte[] bytes = a.getBytes();
        byte[] bytes = a.getBytes("GBK");
        for (byte b : bytes) {
            System.out.print(b + "   ");
            String s = Integer.toBinaryString(b);
            System.out.println(s);
        }
    }
}
