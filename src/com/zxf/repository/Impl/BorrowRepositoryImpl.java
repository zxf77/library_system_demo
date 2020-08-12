package com.zxf.repository.Impl;

import com.zxf.entity.Book;
import com.zxf.entity.Borrow;
import com.zxf.entity.Reader;
import com.zxf.repository.BorrowRepository;
import com.zxf.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            preparedStatement.setString(3, borrowtime);
            preparedStatement.setString(4, returntime);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }
    }

    /**
     * 根据readerid查询这个reader的所有数据
     * @param readerid
     * @return
     */
    @Override
    public List<Borrow> findAllByReaderId(Integer readerid, Integer index, Integer limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select br.id, b.name, b.author, b.publish, br.borrowtime, br.returntime, r.name, r.tel, r.cardid, br.state from borrow br, book b, reader r where readerid = ? and b.id = br.bookid and r.id = br.readerid limit ?, ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Borrow> borrows = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, readerid);
            preparedStatement.setInt(2, index);
            preparedStatement.setInt(3, limit);
            resultSet = preparedStatement.executeQuery();
            //查找出来的表结构
            //+-----+------------+----------+----------------+------------+------------+------+-----+--------+--------+
            //| id  | name       | author   | publish        | borrowtime | returntime | name | tel | cardid | state |
            //+-----+------------+----------+----------------+------------+------------+------+-----+--------+--------+
            //| 219 | 解忧杂货店 | 东野圭吾 | 电子工业出版社 | 2020-03-11 | 2020-03-25 | 李四 | 131 | 001  | 001    | 0  |
            //+-----+------------+----------+----------------+------------+------------+------+-----+--------+--------+
            while (resultSet.next()) {
                //封装成Borrow对象
                borrows.add(new Borrow(resultSet.getInt(1),
                        new Book(resultSet.getString(2), resultSet.getString(3),resultSet.getString(4)),
                        new Reader(resultSet.getString(7), resultSet.getString(8), resultSet.getString(9)),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getInt(10)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return borrows;
    }

    /**
     * 根据id查询一个用户所有的借书数据的总和
     * @param readerid
     * @return
     */
    @Override
    public int count(Integer readerid) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from borrow where readerid = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, readerid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return count;
    }

    /**
     * 根据state找到borrow中没有审核的数据，按页面查询
     * @param state
     * @return
     */
    @Override
    public List<Borrow> findAllByState(Integer state, Integer index, Integer limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select br.id, b.name, b.author, b.publish, br.borrowtime, br.returntime, r.name, r.tel, r.cardid, br.state from borrow br, book b, reader r where state = ? and b.id = br.bookid and r.id = br.readerid limit ?, ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Borrow> borrows = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, state);
            preparedStatement.setInt(2, index);
            preparedStatement.setInt(3, limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                borrows.add(new Borrow(resultSet.getInt(1),
                        new Book(resultSet.getString(2), resultSet.getString(3),resultSet.getString(4)),
                        new Reader(resultSet.getString(7), resultSet.getString(8), resultSet.getString(9)),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getInt(10)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return borrows;
    }

    /**
     * 返回借书数据的总量
     * @param state
     * @return
     */
    @Override
    public int countBorrows(Integer state) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from borrow where state = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, state);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return count;
    }

    /**
     * 管理员处理一条数据的数据库操作方法
     * @param borrowId
     * @param state
     * @param adminId
     */
    @Override
    public void handle(Integer borrowId, Integer state, Integer adminId) {
        Connection connection = JDBCTools.getConnection();
        String sql = "update borrow set state = ?, adminid = ? where id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, state);
            preparedStatement.setInt(2, adminId);
            preparedStatement.setInt(3, borrowId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }
    }
}
