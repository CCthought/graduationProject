package com.adai.service;

import com.adai.entity.User;
import com.adai.vo.request.RegisterUser;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * author Adai
 * create 2019-02-18  14:21
 */
public interface IUserService {

    Integer insert(RegisterUser user);

    String isUniqueAccount(String account);

    Integer login(String account, String password);

    Map<String,Object> getAccountAndMoney(String account);

    Integer logout(String account);

    Integer getBalance(String account);

    Integer recharge(Integer money, String account);
}
