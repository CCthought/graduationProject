package com.adai.service;

import com.adai.utils.PageResult;
import com.adai.vo.request.InsertCartRequest;
import com.adai.vo.response.CartResponse;

import java.util.List;

/**
 * author Adai
 * create 2019-02-25  10:33
 */
public interface ICartService {
    Integer insertCart(InsertCartRequest cart);
    List<CartResponse> getPageListCarts(Integer pageSize, Integer offset,String account);
    PageResult<CartResponse> getPageCarts(Integer pageSize, Integer offset,String account);
    Integer selectCartCounts(String account);
    List<CartResponse> selectCartById(Integer id);

    Integer isExistCart(Integer itemId,String color, Integer size);
    Integer plusCounts(Integer itemId, Integer count);

    Integer changeCount(Integer id, Integer count);

    Integer deleteCartById(Integer id);
    Integer removeAllCarts(String account);
}
