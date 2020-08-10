package com.zxf.repository;

import com.zxf.entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);
}
