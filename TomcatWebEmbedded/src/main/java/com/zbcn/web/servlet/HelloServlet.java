package com.zbcn.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 *@WebServlet 相当于 在web.xml中配置
 * <servlet>
 *     <servlet-name>helloServlet</servlet-name>
 *     <servlet-class>xxx.HelloServlet</servlet-class>
 *   </servlet>
 *   <servlet-mapping>
 *     <servlet-name>helloServlet</servlet-name>
 *     <url-pattern>/hello</url-pattern>
 *   </servlet-mapping>
 * 重定向
 */
@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    /**
     * get 请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        if (name == null) {
            name = "world";
        }
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>Hello, " + name + "!</h1>");
        pw.flush();

    }
}
