package com.adai.service;

import com.adai.entity.User;
import org.springframework.stereotype.Service;

/**
 * author Adai
 * create 2019-02-18  14:21
 */
public interface IUserService {

    Integer insert(User user);

    String isUniqueAccount(String account);

    Integer login(String account, String password);
}
