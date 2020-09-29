package com.zbcn.common.io;

import java.io.*;

/**
 *  缓存流
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/28 14:49
 */
public class BufferDemo {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            fis = new FileInputStream("D:/javaTest/test1.txt");
            bis = new BufferedInputStream(fis);
            fos = new FileOutputStream("D:/javaTest/buffTest3.txt");
            bos = new BufferedOutputStream(fos);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len= bis.read(bytes)) !=-1) {
                System.out.println(new String(bytes,0,len));
                bos.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //如果有缓冲流，我们只需要关闭缓冲流即可。
                //fis.close();
                bis.close();
                //fos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
