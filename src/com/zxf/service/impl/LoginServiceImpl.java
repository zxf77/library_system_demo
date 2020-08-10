package com.zxf.service.impl;

import com.zxf.entity.Admin;
import com.zxf.entity.Reader;
import com.zxf.repository.AdminRepository;
import com.zxf.repository.Impl.AdminRepositoryImpl;
import com.zxf.repository.Impl.ReaderRepositoryImpl;
import com.zxf.repository.ReaderRepository;
import com.zxf.service.LoginService;

public class LoginServiceImpl implements LoginService {
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private AdminRepository adminRepository = new AdminRepositoryImpl();
    @Override
    public Object login(String username, String password, String type) {
        Object object = null;
        switch (type) {
            case "reader":
                object = readerRepository.login(username, password);
                break;
            case "admin":
                object = adminRepository.login(username, password);
                break;
        }
        return object;
    }
}
