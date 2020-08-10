package com.zxf.repository.Impl;

import com.zxf.entity.Book;
import com.zxf.repository.BookRepository;
import com.zxf.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public List<Book> findAll() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from book, bookcase where book.bookcaseid=bookcase.id";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultSet =
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
