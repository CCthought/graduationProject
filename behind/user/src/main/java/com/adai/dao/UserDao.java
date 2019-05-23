package com.adai.dao;

import com.adai.entity.User;
import com.adai.vo.request.RegisterUser;

import java.util.Map;

/**
 * author Adai
 * create 2019-02-18  14:01
 */
public interface UserDao {
    /**
     * 注册用户
     *
     * @param user 用户信息
     * @return 一个int值 如果大于1 则表明插入成功
     */
    Integer insert(RegisterUser user);

    /**
     * 通过account判断是否是唯一的用户，如果是，则返回该用户
     *
     * @param account 用户登录名
     * @return 返回也是account
     */
    String isUniqueAccount(String account);

    /**
     * 用户登录
     *
     * @param account  用户登录名
     * @param password 用户密码
     * @return 一个int值 如果大于1 则表明登录成功
     */
    Integer login(String account, String password);

    /**
     * 通过用户登录名获取用户余额
     *
     * @param account 用户登录名
     * @return 用户登录名和余额
     */
    Map<String, Object> getAccountAndMoney(String account);

    /**
     * 用户退出登录
     *
     * @param account 用户登录名
     * @return 一个int值 如果大于1 则表明退出成功
     */
    Integer logout(String account);

    /**
     * 获取余额
     *
     * @param account 用户登录名
     * @return 返回余额
     */
    Integer getBalance(String account);

    /**
     * 充值
     *
     * @param money   充值的金额
     * @param account 用户登录名
     * @return 一个int值 如果大于1 则表明充值成功
     */
    Integer recharge(Integer money, String account);
}
