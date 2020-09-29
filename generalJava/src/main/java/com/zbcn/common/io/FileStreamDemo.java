package com.zbcn.common.io;

import java.io.*;

/**
 *  文件流测试
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/28 13:29
 */
public class FileStreamDemo {

    public static void main(String[] args) {
        //streamTest();
        readAndWrite();
    }

    /**
     * 字符集操作
     */
    private static void readAndWrite() {
        Reader reader = null;
        Writer wt = null;
        try {
            reader = new FileReader("D:/javaTest/test1.txt");
            wt = new FileWriter("D:/javaTest/test2.txt");
            char[] ch = new char[1024];
            int len = 0;
            while ((len = reader.read(ch)) != -1){
                System.out.println( new String(ch,0,len));
                wt.write(ch,0,len);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
                wt.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void streamTest() {

        InputStream fis = null;
        OutputStream fos = null;
        try {
            fis = new FileInputStream("D:/javaTest/test1.txt");
            //该方法如果文件不存在会自动创建文件
            fos = new FileOutputStream("D:/javaTest/test2.txt");
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fis.read(bytes)) != -1){
                System.out.println(new String(bytes,0,len));
                fos.write(bytes,0,len);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //关闭前回flush
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
