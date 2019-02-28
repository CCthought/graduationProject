package com.adai.controller;

import com.adai.service.ICartService;
import com.adai.utils.ActionResponse;
import com.adai.utils.PageResult;
import com.adai.vo.request.InsertCartRequest;
import com.adai.vo.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ActionResponse insertCart(@RequestBody InsertCartRequest cart) {
        System.err.println(cart);
        Integer INSERT_SUCCESS = cartService.insertCart(cart);
        if(INSERT_SUCCESS == 1){
            return ActionResponse.success();
        } else{
            return new ActionResponse("497","插入失败");
        }
    }

    @RequestMapping(value = "/getPageCarts", method = RequestMethod.GET)
    public ActionResponse getPageCarts(Integer pageSize, Integer currentPage, String account){
        PageResult<CartResponse> pageCarts = cartService.getPageCarts(pageSize, currentPage, account);
        return ActionResponse.success(pageCarts);
    }

    @RequestMapping(value = "/changeCount", method = RequestMethod.GET)
    public ActionResponse changeCount(Integer id, Integer count){
        Integer IS_SUCCESS = cartService.changeCount(id, count);
        if(IS_SUCCESS == 1){
            return ActionResponse.success();
        } else{
            return new ActionResponse("497","更改数量失败");
        }
    }

    @RequestMapping(value = "/deleteCartById", method = RequestMethod.GET)
    public ActionResponse deleteCartById(Integer id){
        Integer IS_SUCCESS = cartService.deleteCartById(id);
        if(IS_SUCCESS == 1){
            return ActionResponse.success();
        } else{
            return new ActionResponse("497","删除失败");
        }
    }

    @RequestMapping(value = "/removeAllCarts", method = RequestMethod.GET)
    public ActionResponse removeAllCarts(String account){
        Integer IS_SUCCESS = cartService.removeAllCarts(account);
        if(IS_SUCCESS == 1){
            return ActionResponse.success();
        } else{
            return new ActionResponse("497","移除失败");
        }
    }

    @RequestMapping(value = "/selectCartById", method = RequestMethod.GET)
    public ActionResponse selectCartById(Integer id){
        List<CartResponse> cartResponse = cartService.selectCartById(id);
        return ActionResponse.success(cartResponse);
    }
}
