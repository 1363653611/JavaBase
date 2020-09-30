package com.zbcn.socket.mail;

import com.sun.mail.pop3.POP3SSLStore;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 *  邮件接受
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/30 10:53
 */
public class MailReceiver {

    // 准备登录信息:
    private static String host = "pop.qq.com";

    private static int port = 995;
    private static String username = "1363653611@qq.com";
    private static String password = "yohkywkznmjigjaj";

    public static void main(String[] args) throws MessagingException {
        // 连接到Store:
        Store store = buildStore();
        Folder folder = buildFolder(store, "INBOX");
        // 获取每一封邮件:
        try {
            handleMessages(folder);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void handleMessages(Folder folder) throws MessagingException, UnsupportedEncodingException {
        Message[] messages = folder.getMessages();
        for (Message message : messages) {
            // 打印每一封邮件:
            printMessage((MimeMessage) message);
        }
    }

    /**
     * 创建Store ,用来接受和存储邮件
     * @return
     * @throws MessagingException
     */
    private static Store buildStore() throws MessagingException {
        Session session = buildSession();
        URLName url = new URLName("pop3", host, port, "", username, password);
        Store store = new POP3SSLStore(session, url);
        store.connect();
        return store;
    }

    /**
     * 获取连接session
     * @return
     */
    public static Session buildSession(){
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "pop3"); // 协议名称
        props.setProperty("mail.pop3.host", host);// POP3主机名
        props.setProperty("mail.pop3.port", String.valueOf(port)); // 端口号

        // 启动SSL:
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", String.valueOf(port));
        Session session = Session.getInstance(props, null);
        session.setDebug(true); // 显示调试信息
        return session;
    }

    //创建指定文件夹("INBOX" 表示收件箱)
    public static Folder buildFolder(Store store,String type){
        try {
            Folder folder = store.getFolder(type);

            // 以读写方式打开:
            folder.open(Folder.READ_WRITE);
            // 打印邮件总数/新邮件数量/未读数量/已删除数量:
            System.out.println("Total messages: " + folder.getMessageCount());
            System.out.println("New messages: " + folder.getNewMessageCount());
            System.out.println("Unread messages: " + folder.getUnreadMessageCount());
            System.out.println("Deleted messages: " + folder.getDeletedMessageCount());
            return folder;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void printMessage(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {
        // 邮件主题:
        System.out.println("Subject: " + MimeUtility.decodeText(msg.getSubject()));
        // 发件人:
        Address[] froms = msg.getFrom();
        InternetAddress address = (InternetAddress) froms[0];
        String personal = address.getPersonal();
        String from = personal == null ? address.getAddress() : (MimeUtility.decodeText(personal) + " <" + address.getAddress() + ">");
        System.out.println("From: " + from);
        // 继续打印收件人:
        Address[] allRecipients = msg.getAllRecipients();
        for (int i = 0; i < allRecipients.length; i++) {
            Address recipient =allRecipients[i];
            System.out.println("Receive"+i + ":"+ recipient);
        }
    }

}
