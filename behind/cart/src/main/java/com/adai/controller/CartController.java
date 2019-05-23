package com.adai.controller;

import com.adai.service.ICartService;
import com.adai.utils.ActionResponse;
import com.adai.utils.PageResult;
import com.adai.vo.request.InsertCartRequest;
import com.adai.vo.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * author Adai
 * create 2019-02-25  10:31
 */
@RestController
@CrossOrigin
public class CartController {

    @Value("${server.port}")
    private String port;
    @Autowired
    private ICartService cartService;

    @RequestMapping(value = "/insertCart", method = RequestMethod.POST)
    public ActionResponse insertCart(@RequestBody InsertCartRequest cart) {
        System.err.println(cart);
        Integer INSERT_SUCCESS = cartService.insertCart(cart);
        if (INSERT_SUCCESS == 1) {
            return ActionResponse.success();
        } else {
            return new ActionResponse("497", "插入失败");
        }
    }

    @RequestMapping(value = "/getPageCarts", method = RequestMethod.GET)
    public ActionResponse getPageCarts(Integer pageSize, Integer currentPage, String account) {
        PageResult<CartResponse> pageCarts = cartService.getPageCarts(pageSize, currentPage, account);
        return ActionResponse.success(pageCarts);
    }

    @RequestMapping(value = "/changeCount", method = RequestMethod.GET)
    public ActionResponse changeCount(Integer id, Integer count) {
        Integer IS_SUCCESS = cartService.changeCount(id, count);
        if (IS_SUCCESS == 1) {
            return ActionResponse.success();
        } else {
            return new ActionResponse("497", "更改数量失败");
        }
    }

    @RequestMapping(value = "/deleteCartById", method = RequestMethod.GET)
    public ActionResponse deleteCartById(Integer id) {
        Integer IS_SUCCESS = cartService.deleteCartById(id);
        if (IS_SUCCESS >= 0) {
            return ActionResponse.success();
        } else {
            return new ActionResponse("497", "删除失败");
        }
    }

    @RequestMapping(value = "/removeAllCarts", method = RequestMethod.GET)
    public ActionResponse removeAllCarts(String account) {
        Integer IS_SUCCESS = cartService.removeAllCarts(account);
        if (IS_SUCCESS == 1) {
            return ActionResponse.success();
        } else {
            return new ActionResponse("497", "移除失败");
        }
    }

    @RequestMapping(value = "/selectCartById", method = RequestMethod.GET)
    public ActionResponse selectCartById(Integer id) {
        List<CartResponse> cartResponse = cartService.selectCartById(id);
        PageResult pageResult = new PageResult<>(5, 1, 1, cartResponse);
        return ActionResponse.success(pageResult);
    }

    @RequestMapping(value = "/getAllMoney", method = RequestMethod.GET)
    public ActionResponse getAllMoney(String account) {
        return ActionResponse.success(cartService.getAllMoney(account));
    }

    @RequestMapping(value = "/getAllCarts", method = RequestMethod.GET)
    public ActionResponse getAllCarts(String account) {
        return ActionResponse.success(cartService.getAllCarts(account));
    }

    @RequestMapping(value = "/deleteAllCarts", method = RequestMethod.GET)
    public ActionResponse deleteAllCarts(String account) {
        Integer IS_SUCCESS = cartService.deleteAllCarts(account);
        if (IS_SUCCESS >= 0) {
            return ActionResponse.success();
        } else {
            return new ActionResponse("497", "删除购物车失败");
        }
    }

    @RequestMapping(value = "/getCartIdByItemIdAndAccount", method = RequestMethod.GET)
    public ActionResponse getCartIdByItemIdAndAccount(Integer itemId, String account, String color, Integer size) {
        return ActionResponse.success(cartService.getCartIdByItemIdAndAccount(itemId, account, color, size));
    }

    @RequestMapping(value = "/deleteCartByAccountAndItemId", method = RequestMethod.GET)
    public String deleteCartByAccountAndItemId(Integer itemId, String account, String color, Integer size) {
        Integer changeNumber = cartService.deleteCartByAccountAndItemId(itemId, account, color, size);
        if (changeNumber == 1) {
            return "200";
        } else {
            return "497";
        }
    }

    /**
     * 该方法执行逻辑和 deleteAllCarts 一样 不过判断条件不同 在这里 changeNumber > 0
     * 而在deleteAllCarts中 changeNumber >= 0 就行了  这里是双保险 Pick feet
     *
     * @param account
     * @return
     */
    @RequestMapping(value = "/deleteAllCartsByRemote", method = RequestMethod.GET)
    public String deleteAllCartsByRemote(String account) {
        Integer IS_SUCCESS = cartService.deleteAllCarts(account);
        if (IS_SUCCESS > 0) {
            return "200";
        } else {
            return "497";
        }
    }

    /**
     *  仅仅用来测试
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello world" + port;
    }
}
