package com.zxf.repository;

public interface BorrowRepository {
    //向borrow表中添加一条借书数据
    void insert(Integer bookid, Integer readerid, String borrowtime, String returntime, Integer adminid, Integer state);
}
