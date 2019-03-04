package com.adai.service;

import com.adai.utils.PageResult;
import com.adai.vo.request.InsertOrder;
import com.adai.vo.response.OrderResponse;
import java.util.List;

/**
 * author Adai
 * create 2019-03-01  13:54
 */
public interface IOrderService {
    Integer insertOrder(InsertOrder order);

    PageResult<OrderResponse> getPageOrders(Integer pageSize, Integer offset, String account);
    List<OrderResponse> getPageListOrders(Integer pageSize, Integer offset, String account);
    Integer selectOrderCounts(String account);

}
