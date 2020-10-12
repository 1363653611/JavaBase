package com.zbcn.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  请求重新转发
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/3 10:53
 */
@WebServlet(urlPatterns = "/hell")
public class ForwardServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 发送重定重新转发:
        req.getRequestDispatcher("/hello").forward(req,resp);
    }
}
