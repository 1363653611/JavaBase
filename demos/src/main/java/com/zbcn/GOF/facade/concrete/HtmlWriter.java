package com.zbcn.GOF.facade.concrete;

import java.io.IOException;
import java.io.Writer;

/**
 *  @title HtmlWriter
 *  @Description 编写web 页面的功能
 *  @author zbcn8
 *  @Date 2020/6/11 14:29
 */
public class HtmlWriter {

    private Writer writer;

    public HtmlWriter(Writer writer) {
        this.writer = writer;
    }

    /**
     * 输出标题
     * @param title
     * @throws IOException
     */
    public void title(String title) throws IOException {
        writer.write("<HTML>");
        writer.write("<HEAD>");
        writer.write("<title>" + title + "</title>");
        writer.write("</HEAD>");
        writer.write("<HTML>\\n");
        writer.write("<body>\\n");
        writer.write("<h1>" + title + "</h1>");
    }

    /**
     * 段落内容
     * @param msg
     * @throws IOException
     */
    public void  paragraph(String msg) throws IOException {
        writer.write("<p>" + msg + "</p>");
    }

    /**
     * 超链接
     * @param href
     * @param caption
     * @throws IOException
     */
    public void link(String href, String caption) throws IOException {
        paragraph("<a href=\\" + href + "\">" + "</a>");
    }

    public void  mainTo(String mail, String userName) throws IOException {

        link("mail to :" + mail,userName);
    }

    /**
     * html 结束
     * @throws IOException
     */
    public void close() throws IOException {
        writer.write("</body>\\n");
        writer.write("</html>\\n");
        writer.close();
    }
}
