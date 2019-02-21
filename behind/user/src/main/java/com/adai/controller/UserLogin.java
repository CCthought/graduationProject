package com.adai.controller;

import com.adai.entity.User;
import com.adai.service.IUserService;
import com.adai.utils.ActionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ActionResponse register(User user) {
        Integer INSERT_SUCCESS = userSerivce.insert(user);
        if (INSERT_SUCCESS == 1) {
            return ActionResponse.success();
        }
        return new ActionResponse<>("499", "插入失败");
    }

    @RequestMapping(value = "/isUniqueAccount", method = RequestMethod.GET)
    private ActionResponse isUniqueAccount(String account) {
        System.err.println(account);
        String uniqueAccount = userSerivce.isUniqueAccount(account);
        if (uniqueAccount == null) {
            return ActionResponse.success();
        } else {
            return new ActionResponse<>("498", "该用户已存在");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ActionResponse login(String account, String password) {
        System.out.println(account + 'a' + password);
        Integer isAccountExist = userSerivce.login(account, password);
        if (isAccountExist == 1) {
            return ActionResponse.success(account);
        } else {
            return new ActionResponse<>("498", "用户名密码不正确");
        }
    }



    //删除用户
    @RequestMapping(value = "/logout", method = RequestMethod.POST, consumes = "application/json")
    public ActionResponse logout(@RequestBody String account) {
        String[] split = account.split("=");
        Integer isDelete = userSerivce.logout(split[1]);
        if(isDelete == 1){
            return ActionResponse.success("1");
        } else {
            return new ActionResponse<>("498", "删除用户失败");
        }
    }
}
