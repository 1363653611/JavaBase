package com.zbcn.socket.mail;

import org.apache.commons.io.FileUtils;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *  邮件发送者
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/30 9:42
 */
public class MailSender {

    /**
     * 邮件服务器地址
     */
    private static String smtp ="smtp.qq.com";

    /**
     * 服务器端口号
     */
    private static String port = "-1";

    /**
     * 用户名
     */
    private static String userName = "1363653611@qq.com";

    /**
     * 登陆口令（授权码）
     */
    private static String pwd = "yohkywkznmjigjaj";


    public static void main(String[] args) {
        Session session = buildMailSession();
        //sendMessage(session);
        //sendHTMLMessage(session);
        //sendAnneMessage(session);
        sendInlinePicMessage(session);
    }

    private static Session buildMailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", smtp); // SMTP主机名
        props.put("mail.smtp.port", port); // 主机端口号
        props.put("mail.smtp.auth", "true"); // 是否需要用户认证
        props.put("mail.smtp.starttls.enable", "true"); // 启用TLS加密

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, pwd);
            }
        });

        // 设置debug模式便于调试:
        session.setDebug(true);
        return session;
    }

    private static void sendMessage(Session session){
        try {
            MimeMessage message = new MimeMessage(session);
            // 设置发送方地址:
            message.setFrom(new InternetAddress(userName));
            // 设置接收方地址:
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("zbcn810@163.com"));
            // 设置邮件主题:
            message.setSubject("test Mail", "UTF-8");
            // 设置邮件正文:
            message.setText("Hi zbcn...", "UTF-8");
            // 发送:
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static void sendHTMLMessage(Session session){
        try {
            MimeMessage message = new MimeMessage(session);
            // 设置发送方地址:
            message.setFrom(new InternetAddress(userName));
            // 设置接收方地址:
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("zbcn810@163.com"));
            // 设置邮件主题:
            message.setSubject("test Mail", "UTF-8");
            // 设置邮件正文:
            message.setText("<h1>Hello</h1><p>Hi, zbcn...</p>", "UTF-8","html");
            // 发送:
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送带附件邮件
     * @param session
     */
    private static void sendAnneMessage(Session session){
        try {
            MimeMessage message = new MimeMessage(session);

            Multipart multipart = new MimeMultipart();
            // 添加text:
            BodyPart textpart = new MimeBodyPart();
            textpart.setContent("带附件邮件", "text/html;charset=utf-8");
            multipart.addBodyPart(textpart);
            // 添加image:
            BodyPart imagepart = new MimeBodyPart();
            imagepart.setFileName("lunix目录说明.jpg");
            InputStream input = getFileAsStream("C:\\Users\\zbcn8\\Pictures\\lunix目录说明.jpg");
            imagepart.setDataHandler(new DataHandler(new ByteArrayDataSource(input, "application/octet-stream")));
            multipart.addBodyPart(imagepart);

            // 设置邮件内容为multipart:
            message.setContent(multipart);

            // 设置发送方地址:
            message.setFrom(new InternetAddress(userName));
            // 设置接收方地址:
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("zbcn810@163.com"));
            // 设置邮件主题:
            message.setSubject("Test Anne Mail", "UTF-8");
            // 发送:
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 发送内嵌图片邮件
     * @param session
     */
    private static void sendInlinePicMessage(Session session){
        try {
            MimeMessage message = new MimeMessage(session);

            Multipart multipart = new MimeMultipart();
            // 添加text:
            BodyPart textpart = new MimeBodyPart();
            textpart.setContent("<h1>内嵌图片邮件</h1><p><img src=\"cid:img01\"></p>", "text/html;charset=utf-8");
            multipart.addBodyPart(textpart);
            // 添加image:
            BodyPart imagepart = new MimeBodyPart();
            imagepart.setFileName("lunix目录说明.jpg");
            InputStream input = getFileAsStream("C:\\Users\\zbcn8\\Pictures\\lunix目录说明.jpg");
            //设置头
            imagepart.setDataHandler(new DataHandler(new ByteArrayDataSource(input, "image/jpeg")));
            // 与HTML的<img src="cid:img01">关联:
            imagepart.setHeader("Content-ID", "<img01>");
            multipart.addBodyPart(imagepart);

            // 设置邮件内容为multipart:
            message.setContent(multipart);

            // 设置发送方地址:
            message.setFrom(new InternetAddress(userName));
            // 设置接收方地址:
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("zbcn810@163.com"));
            // 设置邮件主题:
            message.setSubject("Test Inline Pic Mail", "UTF-8");
            // 发送:
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    public static InputStream getFileAsStream(String filePath){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = FileUtils.openInputStream(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileInputStream;
    }
}
