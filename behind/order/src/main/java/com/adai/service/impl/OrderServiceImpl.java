package com.adai.service.impl;

import com.adai.dao.OrderDao;
import com.adai.service.IOrderService;
import com.adai.utils.DateFormatUtil;
import com.adai.utils.PageResult;
import com.adai.vo.request.InsertOrder;
import com.adai.vo.response.OrderResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * author Adai
 * create 2019-03-01  13:55
 */
@Service
public class OrderServiceImpl implements IOrderService{

    @Resource
    private OrderDao orderDao;

    @Override
    @Transactional
    public Integer insertOrder(InsertOrder order) {
        Integer INSERT_SUCCESS = orderDao.insertOrder(order);
        if(INSERT_SUCCESS == 1){
            Integer UPDATE_SUCCESS = orderDao.updateSaled(order.getItemId(), order.getCount());
            if(UPDATE_SUCCESS == 1){
                return UPDATE_SUCCESS;
            }else{
                return -1;
            }
        } else{
            return -1;
        }
    }

    @Override
    public PageResult<OrderResponse> getPageOrders(Integer pageSize, Integer offset, String account) {
        // 该处的offset 是前台传过来的currentPage
        return new PageResult<>(pageSize,this.selectOrderCounts(account),offset,
                this.getPageListOrders(pageSize,(offset - 1) * pageSize,account));
    }

    @Override
    public List<OrderResponse> getPageListOrders(Integer pageSize, Integer offset, String account) {
        List<OrderResponse> pageListOrders = orderDao.getPageListOrders(pageSize, offset, account);
        for(OrderResponse orderResponse : pageListOrders){
            orderResponse.setStrOrderTime(DateFormatUtil.longToString(orderResponse.getOrderTime()));
        }
        return pageListOrders;
    }

    @Override
    public Integer selectOrderCounts(String account) {
        return orderDao.selectOrderCounts(account);
    }

}
