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

    Integer insertOrder(InsertOrder order);

    List<OrderResponse> getPageListOrders(Integer pageSize, Integer offset, String account);
    Integer selectOrderCounts(String account);

    Integer updateSaled(@Param("itemId") Integer itemId,@Param("count") Integer count);

}
