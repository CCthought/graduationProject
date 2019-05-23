package com.adai.dao;

import com.adai.vo.request.InsertOrder;
import com.adai.vo.response.OrderResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author Adai
 * create 2019-03-01  13:54
 */
public interface OrderDao {

    /**
     * 插入订单
     * @param order 订单实体
     * @return 返回1，则表明插入成功，否则失败
     */
    Integer insertOrder(InsertOrder order);

    /**
     * 分页查询 获取订单
     * @param pageSize 分页大小
     * @param offset 定位
     * @param account 用户登录名
     * @return 数据集合
     */
    List<OrderResponse> getPageListOrders(Integer pageSize, Integer offset, String account);

    /**
     * 获取指定订单的总数量
     * @param account 用户登录名
     * @return 总数量
     */
    Integer selectOrderCounts(String account);

    /**
     * 更新商品销售数量
     * @param itemId 商品ID
     * @param count 数量
     * @return 返回1，则表明更新成功，否则失败
     */
    Integer updateSaled(@Param("itemId") Integer itemId,@Param("count") Integer count);

}
