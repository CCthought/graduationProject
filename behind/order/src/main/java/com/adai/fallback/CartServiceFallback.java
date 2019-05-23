package com.adai.fallback;

import com.adai.feign.CartService;
import org.springframework.stereotype.Component;

/**
 * @author zhouchengpei
 * create 2019-04-27  11:15
 * description
 */
@Component
public class CartServiceFallback implements CartService {
    @Override
    public String deleteCartByAccountAndItemId(Integer itemId, String account, String color, Integer size) {
        System.err.println("feign 调用cart-service deleteCartByAccountAndItemId 异常");
        return null;
    }

    @Override
    public String deleteAllCartsByRemote(String account) {
        System.err.println("feign 调用cart-service deleteAllCartsByRemote 异常");
        return null;
    }
}
