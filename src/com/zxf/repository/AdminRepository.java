package com.zxf.repository;

import com.zxf.entity.Admin;

public interface AdminRepository {
    Admin login(String username, String password);
}
