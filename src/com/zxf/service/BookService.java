package com.zxf.service;

import com.zxf.entity.Book;

import java.util.List;

/**
 * 和图书相关的业务写在这里面
 */
public interface BookService {
    //查询所有图书
    List<Book> findAll(int page);
    int getPages();
}
