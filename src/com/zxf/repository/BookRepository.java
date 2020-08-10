package com.zxf.repository;

import com.zxf.entity.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll(int index, int limit);
    int count();
}
