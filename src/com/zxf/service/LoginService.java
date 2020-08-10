package com.zxf.service;

import com.zxf.entity.Reader;

public interface LoginService {
    //判断能否登录成功
    public Object login(String username, String password, String type);
}
