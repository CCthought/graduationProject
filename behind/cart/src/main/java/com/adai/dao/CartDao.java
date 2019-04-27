package com.adai.dao;

import com.adai.vo.request.InsertCartRequest;
import com.adai.vo.response.CartResponse;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 判断衣服 裤子 类别
     * @param itemId
     * @param color
     * @param size
     * @param account 用户名称
     * @return
     */
    Integer isExistCartHasColorSize(Integer itemId, String color, Integer size,String account);

    /**
     *     判断 书籍 食物 类别
     */

    Integer isExistCartNoColorSize(Integer itemId,String account);

    Integer plusCounts(Integer itemId, Integer count,String account);

    Integer changeCount(Integer id, Integer count);

    Integer deleteCartById(Integer id);

    Integer removeAllCarts(String account);

    /**
     * 该方法返回值 和 Service不一样
     */

    List<Map<String,Integer>> getAllMoney(String account);

    /**
     * // 该接口专门为 确定订单的时候  拿到所有的购物车数据 进行结算
     * @param account
     * @return
     */
    List<CartResponse> getAllCarts(String account);

    Integer deleteAllCarts(String account);

    /**
     * 专门为 立即购买 按钮 准备的接口 通过itemId account 拿到cartId
     * @param itemId
     * @param account
     * @param color
     * @param size
     * @return
     */
    Integer getCartIdByItemIdAndAccount(Integer itemId, String account, String color, Integer size);

    /**
     * 专属远程调用的方法
     * @return
     */
    Integer deleteCartByAccountAndItemId(@Param("itemId") Integer itemId, @Param("account") String account);
}
