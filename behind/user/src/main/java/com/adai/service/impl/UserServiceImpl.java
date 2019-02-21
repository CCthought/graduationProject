package com.adai.service.impl;

import com.adai.dao.UserDao;
import com.adai.entity.User;
import com.adai.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author Adai
 * create 2019-02-18  14:22
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userdao;

    @Override
    public Integer insert(User user) {
        return userdao.insert(user);
    }

    @Override
    public String isUniqueAccount(String account) {
        return userdao.isUniqueAccount(account);
    }

    @Override
    public Integer login(String account, String password) {
        return userdao.login(account, password);
    }

    @Override
    public Integer logout(String account) {
        return userdao.logout(account);
    }
}
