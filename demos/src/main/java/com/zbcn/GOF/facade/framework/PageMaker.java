package com.zbcn.GOF.facade.framework;

import com.zbcn.GOF.facade.concrete.DataBase;
import com.zbcn.GOF.facade.concrete.HtmlWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * 门面类
 */
public class PageMaker {

    public static void makeWelcomePage(String mail, String fileName){
        Properties mailData = DataBase.getProperties("mailData");
        String userName = mailData.getProperty(mail);
        try {
            HtmlWriter htmlWriter = new HtmlWriter(new FileWriter(fileName));
            htmlWriter.title("welcome to my page...");
            htmlWriter.paragraph(userName + "欢迎来到 我的主页" );
            htmlWriter.paragraph("等着你的邮件哦。。。" );
            htmlWriter.mainTo(mail, userName);
            htmlWriter.close();
            System.out.println("文件生成成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
