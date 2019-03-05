package com.adai.controller;

import com.adai.service.IUserService;
import com.adai.utils.ActionResponse;
import com.adai.vo.request.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * author Adai
 * create 2019-02-17  21:38
 */
@RestController
@CrossOrigin
public class UserLogin {
    @Autowired
    private IUserService userSerivce;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ActionResponse register(RegisterUser user){
        // 由于前端是表单提交       data: $('#myForm').serialize(), 所以这里的注册时间 在后台处理
        user.setRegisterTime(System.currentTimeMillis() / 1000);
        Integer INSERT_SUCCESS = userSerivce.insert(user);
        if (INSERT_SUCCESS == 1) {
            return ActionResponse.success();
        }
        return new ActionResponse<>("497", "插入失败");
    }

    @RequestMapping(value = "/isUniqueAccount", method = RequestMethod.GET)
    private ActionResponse isUniqueAccount(String account) {
        String uniqueAccount = userSerivce.isUniqueAccount(account);
        if (uniqueAccount == null) {
            return ActionResponse.success();
        } else {
            return new ActionResponse<>("497", "该用户已存在");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ActionResponse login(String account, String password) {
        Integer isAccountExist = userSerivce.login(account, password);
        if (isAccountExist == 1) {
            Map<String, Object> accountAndMoney = userSerivce.getAccountAndMoney(account);
            return ActionResponse.success(accountAndMoney);
        } else {
            return new ActionResponse<>("497", "用户名密码不正确");
        }
    }


    //删除用户  没有被用过
    @RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = "application/json")
    public ActionResponse logout(@RequestBody String account) {
        String[] split = account.split("=");
        Integer isDelete = userSerivce.logout(split[1]);
        if (isDelete == 1) {
            return ActionResponse.success();
        } else {
            return new ActionResponse<>("497", "删除用户失败");
        }
    }

    // 查询用户余额
    @RequestMapping(value = "/getBalance", method = RequestMethod.GET)
    public ActionResponse getBalance(String account) {
        Integer balance = userSerivce.getBalance(account);
        return ActionResponse.success(balance);
    }

    // 该接口 作为 订单结算 和 充值
    @RequestMapping(value = "/recharge", method = RequestMethod.GET)
    public ActionResponse recharge(Integer money, String account) {
        Integer IS_SUCCESS = userSerivce.recharge(money, account);
        if (IS_SUCCESS == 1) {
            return ActionResponse.success();
        } else {
            return new ActionResponse<>("497", "充值失败");
        }
    }
}