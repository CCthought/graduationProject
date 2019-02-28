package com.adai.dao;

import com.adai.vo.request.InsertCartRequest;
import com.adai.vo.response.CartResponse;

import java.util.List;
import java.util.Map;

/**
 * author Adai
 * create 2019-02-25  10:34
 */
public interface CartDao {
    Integer insertCart(InsertCartRequest cart);

    List<CartResponse> getPageListCarts(Integer pageSize, Integer offset, String account);

    Integer selectCartCounts(String account);

    List<CartResponse> selectCartById(Integer id);

    // 判断衣服 裤子 类别
    Integer isExistCartHasColorSize(Integer itemId, String color, Integer size);

    // 判断 书籍 食物 类别
    Integer isExistCartNoColorSize(Integer itemId);

    Integer plusCounts(Integer itemId, Integer count);

    Integer changeCount(Integer id, Integer count);

    Integer deleteCartById(Integer id);

    Integer removeAllCarts(String account);

    // 该方法返回值 和 Service不一样
    List<Map<String,Integer>> getAllMoney(String account);
}
