package com.zxf.repository.Impl;

import com.zxf.entity.Admin;
import com.zxf.repository.AdminRepository;
import com.zxf.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepositoryImpl implements AdminRepository {
    @Override
    public Admin login(String username, String password) {
        //连接数据库
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from bookadmin where username = ? and password = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Admin admin = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            //判断是否存在这个admin数据
            if (resultSet.next()) {
                admin = new Admin(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return admin;
    }
}
