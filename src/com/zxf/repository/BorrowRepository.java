package com.zxf.repository;

import com.zxf.entity.Borrow;

import java.util.List;

public interface BorrowRepository {
    //向borrow表中添加一条借书数据
    void insert(Integer bookid, Integer readerid, String borrowtime, String returntime, Integer adminid, Integer state);
    //查询一个用户所有的借书数据
    List<Borrow> findAllByReaderId(Integer readerid, Integer index, Integer limit);
}
