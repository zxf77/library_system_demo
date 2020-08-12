package com.zxf.controller;

import com.zxf.entity.Book;
import com.zxf.entity.Borrow;
import com.zxf.entity.Reader;
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

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null) {
            method = "findAll";
        }
        //从session中获得当前登录的用户信息
        HttpSession httpSession = req.getSession();
        Reader reader = (Reader) httpSession.getAttribute("reader");
        switch (method) {
            case "findAll":
                String pageStr = req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                List<Book> books = bookService.findAll(page);
                req.setAttribute("books", books);
                req.setAttribute("dataPrePage", 6);
                req.setAttribute("currentPage", page);
                req.setAttribute("pages", bookService.getPages());
                //跳转到读者首页
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                break;
            case "addBorrow":
                String bookidStr = req.getParameter("bookid");
                Integer bookid = Integer.parseInt(bookidStr);
                //调用方法来添加借书数据
                bookService.addBorrow(bookid, reader.getId());
                resp.sendRedirect("/book?page=1");
                break;
            case "findAllBorrow":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                //按页展示当前用户在这个页面的借书数据
                List<Borrow> borrowList = bookService.findAllBorrowByReaderId(reader.getId(), page);
                req.setAttribute("list", borrowList);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages", bookService.getBorrowPages(reader.getId()));
                req.getRequestDispatcher("borrow.jsp").forward(req, resp);
                break;
        }
    }
}
