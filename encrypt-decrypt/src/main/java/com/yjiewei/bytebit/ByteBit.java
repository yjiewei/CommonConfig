/*
 * @author yjiewei
 * @date 2021/1/29 10:26
 */
package com.yjiewei.bytebit;

/**
 * 打印出来应该是8个bit，但前面是0，没有打印 ，从打印结果可以看出来，一个英文字符 ，占一个字节
 */
public class ByteBit {
    public static void main(String[] args) {
        String a = "a";
        byte[] bytes = a.getBytes();
        for (byte b : bytes) {
            int c = b;
            // 打印发现byte实际上就是ascii码
            System.out.println(c);

            // 我们在来看看每个byte对应的bit，byte获取对应的bit
            String s = Integer.toBinaryString(c);
            System.out.println(s);
        }
    }
}
