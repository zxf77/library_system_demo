package com.zxf.controller;

import com.zxf.entity.Book;
import com.zxf.service.BookService;
import com.zxf.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        Integer page = Integer.parseInt(pageStr);
        List<Book> books = bookService.findAll(page);
        req.setAttribute("books", books);
        req.setAttribute("dataPrePage", 6);
        req.setAttribute("currentPage", page);
        req.setAttribute("pages", bookService.getPages());
        //跳转到读者首页
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
