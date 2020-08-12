package com.zxf.repository;

import com.zxf.entity.Borrow;

import java.util.List;

public interface BorrowRepository {
    //向borrow表中添加一条借书数据
    void insert(Integer bookid, Integer readerid, String borrowtime, String returntime, Integer adminid, Integer state);
    //查询一个用户在某个页面的所有的借书数据
    List<Borrow> findAllByReaderId(Integer readerid, Integer index, Integer limit);
    //查询某个用户借书数据的总和
    int count(Integer readerid);
    //找到未审核的数据，按页来展示
    List<Borrow> findAllByState(Integer state, Integer index, Integer limit);
    //找到未审核数据
    int countBorrows(Integer state);
}
