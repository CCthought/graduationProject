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
    /**
     * 插入购物车
     *
     * @param cart 购物车实体
     * @return 如果等于1，表明插入成功，否则失败
     */
    Integer insertCart(InsertCartRequest cart);

    /**
     * 通过指定的account，进行分页查询
     *
     * @param pageSize 分页大小
     * @param offset   指定位置
     * @param account  指定用户名
     * @return 数据集合
     */
    List<CartResponse> getPageListCarts(Integer pageSize, Integer offset, String account);

    /**
     * 通过指定的account，返回相应的总数量
     *
     * @param account 用户登录名
     * @return 总数量
     */
    Integer selectCartCounts(String account);

    /**
     * 通过ID 查询购物车
     *
     * @param id 数据库唯一ID
     * @return 数据集合
     */
    List<CartResponse> selectCartById(Integer id);

    /**
     * 判断衣服 裤子 类别
     *
     * @param itemId
     * @param color
     * @param size
     * @param account 用户名称
     * @return 返回总数量
     */
    Integer isExistCartHasColorSize(Integer itemId, String color, Integer size, String account);

    /**
     * 判断购物车是否存在
     *
     * @param itemId  商品ID
     * @param account 用户登录名
     * @return 总数量
     */
    Integer isExistCartNoColorSize(Integer itemId, String account);

    /**
     * 购物车数量+1
     *
     * @param itemId  商品ID
     * @param count   数量
     * @param account 用户登录名
     * @return 如果等于1，表明插入成功，否则失败
     */
    Integer plusCounts(Integer itemId, Integer count, String account);

    /**
     * 改变购物车商品数量
     *
     * @param id    数据库唯一ID
     * @param count 数量
     * @return 如果等于1，表明插入成功，否则失败
     */
    Integer changeCount(Integer id, Integer count);

    /**
     * 删除一个购物车
     *
     * @param id 数据库唯一ID
     * @return 如果等于1，表明插入成功，否则失败
     */
    Integer deleteCartById(Integer id);

    /**
     * 删除所有购物车
     *
     * @param account 用户登录名
     * @return 如果等于1，表明插入成功，否则失败
     */
    Integer removeAllCarts(String account);


    /**
     * 获取该用户购物车所有金额（该方法返回值和Service不一样）
     *
     * @param account 用户登录名
     * @return 数据集合
     */
    List<Map<String, Integer>> getAllMoney(String account);

    /**
     * 该接口专门为 确定订单的时候  拿到所有的购物车数据 进行结算
     *
     * @param account 用户登录名
     * @return 数据集合
     */
    List<CartResponse> getAllCarts(String account);

    /**
     * 删除所有购物车
     *
     * @param account 用户登录名
     * @return 如果等于1，表明插入成功，否则失败
     */
    Integer deleteAllCarts(String account);

    /**
     * 专门为 立即购买 按钮 准备的接口 通过itemId account 拿到cartId
     *
     * @param itemId  商品ID
     * @param account 用户登录名
     * @param color   颜色
     * @param size    尺寸
     * @return 如果等于1，表明插入成功，否则失败
     */
    Integer getCartIdByItemIdAndAccount(Integer itemId, String account, String color, Integer size);

    /**
     * 专属远程调用的方法
     * @param itemId 商品ID
     * @param account 用户登录名
     * @return 如果>=1，表明调用远程方法成功
     */
    Integer deleteCartByAccountAndItemId(@Param("itemId") Integer itemId, @Param("account") String account,
                                         @Param("color") String color, @Param("size") Integer size);
}
