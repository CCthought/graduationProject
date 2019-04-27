package com.adai.service;

import com.adai.utils.PageResult;
import com.adai.vo.request.InsertCartRequest;
import com.adai.vo.response.CartResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * author Adai
 * create 2019-02-25  10:33
 */
public interface ICartService {
    Integer insertCart(InsertCartRequest cart);

    List<CartResponse> getPageListCarts(Integer pageSize, Integer offset, String account);

    PageResult<CartResponse> getPageCarts(Integer pageSize, Integer offset, String account);

    Integer selectCartCounts(String account);

    List<CartResponse> selectCartById(Integer id);

    Integer isExistCartHasColorSize(Integer itemId, String color, Integer size,String account);

    Integer isExistCartNoColorSize(Integer itemId, String account);

    Integer plusCounts(Integer itemId, Integer count,String account);

    Integer changeCount(Integer id, Integer count);

    Integer deleteCartById(Integer id);

    Integer removeAllCarts(String account);

    /**
     * 该方法返回值 和dao不一样
     * @param account
     * @return
     */
    Integer getAllMoney(String account);

    /**
     * 该接口专门为 确定订单的时候  拿到所有的购物车数据 进行结算
     * @param account
     * @return
     */
    List<CartResponse> getAllCarts(String account);

    Integer deleteAllCarts(String account);

    Integer getCartIdByItemIdAndAccount(Integer itemId, String account, String color, Integer size);

    /**
     * 专属远程调用的方法
     * @return
     */
    Integer deleteCartByAccountAndItemId(@Param("itemId") Integer itemId, @Param("account") String account);
}
