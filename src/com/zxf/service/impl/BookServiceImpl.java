package com.zxf.service.impl;

import com.zxf.entity.Book;
import com.zxf.repository.BookRepository;
import com.zxf.repository.Impl.BookRepositoryImpl;
import com.zxf.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository = new BookRepositoryImpl();
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
