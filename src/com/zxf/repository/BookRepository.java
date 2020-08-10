package com.zxf.repository;

import com.zxf.entity.Book;

import java.util.List;

public interface BookRepository {
    public List<Book> findAll();
}
