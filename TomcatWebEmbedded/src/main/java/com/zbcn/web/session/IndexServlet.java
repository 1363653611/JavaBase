package com.zbcn.web.session;

import com.zbcn.web.utils.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    // GET请求时显示登录页:
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = (String)req.getSession().getAttribute("user");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("X-Powered-By", "JavaEE Servlet");
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>Welcome, " + (user != null ? user : "Guest") + "</h1>");
        if (user == null) {
            // 未登录，显示登录链接:
            pw.write("<p><a href=\"/signIn\">Sign In</a></p>");
        } else {
            // 已登录，显示登出链接:
            pw.write("<p><a href=\"/signOut\">Sign Out</a></p>");
        }
        pw.write("<br/>");
        //设置语言
        pw.write("<p><a href=\"/pref?lang=zh\">中文</a></p>");
        pw.write("<p><a href=\"/pref?lang=en\">English</a></p>");
        //lang 处理
        String s = WebUtils.parseLanguageFromCookie(req);
        pw.write("<h2>用户设置语言lang:" + s +"</h2>");
        pw.flush();
    }


}
