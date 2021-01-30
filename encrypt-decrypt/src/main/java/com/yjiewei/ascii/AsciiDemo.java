/*
 * @author yjiewei
 * @date 2021/1/28 20:56
 */
package com.yjiewei.ascii;

public class AsciiDemo {
    public static void main(String[] args) {
        printAscii();

        printStringsToAscii();

    }

    public static void printStringsToAscii() {
        String c = "AaZ";
        // 获取ascii码，需要把字符串转成字符
        char[] chars = c.toCharArray();
        for (char cc : chars) {
            int asciiCode = cc;
            System.out.println(asciiCode);
        }
    }

    public static void printAscii() {
        char a = 'A';
        int b = a;
        // 打印ascii码
        System.out.println(b);
    }
}
