package com.adai.service.impl;

import com.adai.dao.OrderDao;
import com.adai.service.IOrderService;
import com.adai.vo.request.InsertOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author Adai
 * create 2019-03-01  13:55
 */
@Service
public class OrderServiceImpl implements IOrderService{

    @Resource
    private OrderDao orderDao;

    @Override
    public Integer insertOrder(InsertOrder order) {
        return orderDao.insertOrder(order);
    }
}
