package com.zxf.service;

import com.zxf.entity.Book;
import com.zxf.entity.Borrow;

import java.util.List;

/**
 * 和图书相关的业务写在这里面
 */
public interface BookService {
    //查询所有图书
    List<Book> findAll(int page);
    //得到总共的页数
    int getPages();
    //向借书的数据库中添加一条数据。
    void addBorrow(Integer bookid, Integer readerid);
    //查找一个用户所有借书记录
    List<Borrow> findAllBorrowByReaderId(Integer readerid, Integer page);
}
