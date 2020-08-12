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
    //查找一个用户在这个页面的借书记录
    List<Borrow> findAllBorrowByReaderId(Integer readerid, Integer page);
    //获取某个用户所有借书数据的一共的页数
    int getBorrowPages(Integer readerid);
    //找到数据库中所有未审核的借书数据，按页面展示
    List<Borrow> finAllBorrowByState(Integer state, Integer page);
    //得到未审核的数据一共有多少页
    int getPagesState(Integer state);
}
