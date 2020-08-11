package com.zxf.repository.Impl;

import com.zxf.repository.BorrowRepository;
import com.zxf.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrowRepositoryImpl implements BorrowRepository {

    /**
     * 向borrow表中添加一条借书数据
     * @param bookid
     * @param readerid
     * @param borrowtime
     * @param returntime
     * @param adminid
     * @param state
     */
    @Override
    public void insert(Integer bookid, Integer readerid, String borrowtime, String returntime, Integer adminid, Integer state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into borrow(bookid, readerid, borrowtime, returntime, state) values(?, ?, ?, ?, 0)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookid);
            preparedStatement.setInt(2, readerid);
            preparedStatement.setString(2, borrowtime);
            preparedStatement.setString(3, returntime);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }
    }
}
