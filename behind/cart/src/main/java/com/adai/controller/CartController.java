package com.adai.controller;

import com.adai.entity.Cart;
import com.adai.service.ICartService;
import com.adai.utils.ActionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * author Adai
 * create 2019-02-25  10:31
 */
@RestController
@CrossOrigin
public class CartController {

    @Autowired
    private ICartService cartService;

    @RequestMapping(value = "/insertCart", method = RequestMethod.POST)
    public ActionResponse insertCart(@RequestBody Cart cart) {
        System.err.println(cart);
        Integer INSERT_SUCCESS = cartService.insertCart(cart);
        if(INSERT_SUCCESS == 1){
            return ActionResponse.success();
        } else{
            return new ActionResponse("497","插入失败");
        }
    }

}
