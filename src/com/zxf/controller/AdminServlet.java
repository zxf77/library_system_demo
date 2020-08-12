package com.zxf.controller;

import com.zxf.entity.Admin;
import com.zxf.entity.Borrow;
import com.zxf.service.BookService;
import com.zxf.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null) {
            method = "findAllBorrow";
        }
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        switch (method) {
            case "findAllBorrow":
                String pageStr = req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                List<Borrow> borrows = bookService.finAllBorrowByState(0, page);
                req.setAttribute("list", borrows);
                req.setAttribute("dataPrePage", 6);
                req.setAttribute("currentPage", page);
                req.setAttribute("pages", bookService.getPagesState(0));
                req.getRequestDispatcher("admin.jsp").forward(req, resp);
                break;
            case "handle":
                String idStr = req.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                String stateStr = req.getParameter("state");
                Integer state = Integer.parseInt(stateStr);
                bookService.handleBorrow(id, state, admin.getId());
                resp.sendRedirect("/admin?page=1");
                break;
        }
    }
}
