package com.zxf.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Reader;

@WebFilter("/book")
public class ReaderFilter implements Filter {

    /**
     * 判断是否有读者登录
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            System.out.println("hello");
            ((HttpServletResponse)servletResponse).sendRedirect("login.jsp");
        } else {
            System.out.println("nihao");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
