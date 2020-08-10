package com.zxf.service.impl;

import com.zxf.entity.Book;
import com.zxf.repository.BookRepository;
import com.zxf.repository.Impl.BookRepositoryImpl;
import com.zxf.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    //设置每页展示的书数量
    private final int LIMIT = 6;
    private BookRepository bookRepository = new BookRepositoryImpl();
    @Override
    public List<Book> findAll(int page) {
        int index = (page - 1) * LIMIT;
        return bookRepository.findAll(index, LIMIT);
    }

    @Override
    public int getPages() {
        //获得数据库中所有书籍的数量
        int count = bookRepository.count();
        int page = 0;
        if (count % LIMIT == 0) {
            page = count / LIMIT;
        } else {
            page = count / LIMIT + 1;
        }
        return page;
    }
}
