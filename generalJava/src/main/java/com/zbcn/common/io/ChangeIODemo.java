package com.zbcn.common.io;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 *  转换流
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/28 15:30
 */
public class ChangeIODemo {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = null;
        BufferedReader bis = null;
        //System.in 标准输入流是InputStream类的实例,所以需要将其转换为字符流
        reader = new InputStreamReader(System.in);
        bis = new BufferedReader(reader);

        //输出流，OutputStreamWriter 将字符流转换为字节流
        BufferedWriter buffWriter = null;
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        buffWriter = new BufferedWriter(writer);

        String str;
        try {
            while ((str = bis.readLine()) != null){
                if(StringUtils.equals(str,"exit")){
                    System.exit(1);
                }
                System.out.println("输出内容为：" + str);
                buffWriter.write(str);
                buffWriter.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {

        }
    }
}
