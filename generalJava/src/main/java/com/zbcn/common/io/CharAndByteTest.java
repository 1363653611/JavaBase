package com.zbcn.common.io;
/**
 *  字符与字节测试
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/28 11:46
 */
public class CharAndByteTest {

    public static void main(String[] args) {
        String str = "你好hello";
        int length = str.getBytes().length;
        int len = str.length();
        System.out.println("字节长度为：" + length);
        System.out.println("字符长度为：" + len);
        System.out.println("系统默认编码方式：" + System.getProperty("file.encoding"));
    }
}
