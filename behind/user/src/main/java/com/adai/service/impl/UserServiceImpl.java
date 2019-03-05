package com.adai.service.impl;

import com.adai.dao.UserDao;
import com.adai.entity.User;
import com.adai.service.IUserService;
import com.adai.vo.request.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * author Adai
 * create 2019-02-18  14:22
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;

    @Override
    public Integer insert(RegisterUser user) {
        return userDao.insert(user);
    }

    @Override
    public String isUniqueAccount(String account) {
        return userDao.isUniqueAccount(account);
    }

    @Override
    public Integer login(String account, String password) {
        return userDao.login(account, password);
    }

    @Override
    public Map<String, Object> getAccountAndMoney(String account) {
        return userDao.getAccountAndMoney(account);
    }

    @Override
    public Integer logout(String account) {
        return userDao.logout(account);
    }

    @Override
    public Integer getBalance(String account) {
        return userDao.getBalance(account);
    }

    @Override
    public Integer recharge(Integer money, String account) {
        return userDao.recharge(money, account);
    }
}
