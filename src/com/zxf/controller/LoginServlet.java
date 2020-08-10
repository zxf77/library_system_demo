package com.zxf.controller;

import com.zxf.entity.Admin;
import com.zxf.entity.Reader;
import com.zxf.service.LoginService;
import com.zxf.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();

    /**
     * 处理登录请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前端传来的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");         //用户类型，reader或者admin

        //判断Reader是否为null
        Object object = loginService.login(username, password, type);
        if (object != null) {
            //将登录信息存到session中
            HttpSession session = req.getSession();
            //根据不同的type存不同的key
            switch (type) {
                case "reader":
                    Reader reader = (Reader) object;
                    session.setAttribute("reader", reader);
                    //跳转到读者首页
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                    req.setAttribute("list", );
                    break;
                case "admin":
                    Admin admin = (Admin) object;
                    session.setAttribute("admin", admin);
                    //跳转到管理员首页
                    break;
            }
        } else {
            resp.sendRedirect("login.jsp");
        }
    }
}
