package com.zxf.repository;

import com.zxf.entity.Reader;

public interface ReaderRepository {
    //判断数据库中是否有这个读者数据
    Reader login(String username, String password);
}
