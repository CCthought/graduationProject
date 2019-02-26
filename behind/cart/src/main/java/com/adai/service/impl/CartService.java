package com.adai.service.impl;

import com.adai.dao.CartDao;
import com.adai.entity.Cart;
import com.adai.service.ICartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author Adai
 * create 2019-02-25  10:34
 */
@Service
public class CartService implements ICartService{

    @Resource
    private CartDao cartdao;

    @Override
    public Integer insertCart(Cart cart) {
        return cartdao.insertCart(cart);
    }
}
