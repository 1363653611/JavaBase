package com.zbcn.web.cookie;

import com.google.common.collect.Sets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 *  cookie 设置国际话
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/3 11:48
 */
@WebServlet(urlPatterns = "/pref")
public class CookieServlet extends HttpServlet {

    private static final Set<String> LANGUAGES = Sets.newHashSet("en", "zh");

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String lang = req.getParameter("lang");
        if (LANGUAGES.contains(lang)) {
            // 创建一个新的Cookie:
            Cookie cookie = new Cookie("lang", lang);
            // 该Cookie生效的路径范围:
            cookie.setPath("/index");
            // 该Cookie有效期:
            cookie.setMaxAge(8640000); // 8640000秒=100天
            // 将该Cookie添加到响应:
            resp.addCookie(cookie);
        }
        resp.sendRedirect("/index");
    }
}
