package com.adai.dao;

import com.adai.vo.request.InsertOrder;

/**
 * author Adai
 * create 2019-03-01  13:54
 */
public interface OrderDao {

    Integer insertOrder(InsertOrder order);
}
