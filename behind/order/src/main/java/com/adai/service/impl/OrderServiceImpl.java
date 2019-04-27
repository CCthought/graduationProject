package com.adai.service.impl;

import com.adai.dao.OrderDao;
import com.adai.feign.CartService;
import com.adai.service.IOrderService;
import com.adai.utils.ActionResponse;
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
public class OrderServiceImpl implements IOrderService {

    @Resource
    private OrderDao orderDao;

    /**
     * 注入远程调用对象
     */
    @Resource
    private CartService cartService;

    public static final String REMOTE_SUCCESS = "200";

    @Override
    @Transactional
    public Integer insertOrder(InsertOrder order) {
        Integer changeNumber = orderDao.insertOrder(order);
        if (changeNumber == 1) {
            Integer updateChangeNumber = orderDao.updateSaled(order.getItemId(), order.getCount());
            if (updateChangeNumber == 1) {
                // 更新成功 调用远程购物车服务 删除购物车
                String remoteResult = cartService.deleteCartByAccountAndItemId(order.getItemId(), order.getAccount());
                if (REMOTE_SUCCESS.equals(remoteResult)) {
                    return updateChangeNumber;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    @Override
    public PageResult<OrderResponse> getPageOrders(Integer pageSize, Integer offset, String account) {
        // 该处的offset 是前台传过来的currentPage
        return new PageResult<>(pageSize, this.selectOrderCounts(account), offset,
                this.getPageListOrders(pageSize, (offset - 1) * pageSize, account));
    }

    @Override
    public List<OrderResponse> getPageListOrders(Integer pageSize, Integer offset, String account) {
        List<OrderResponse> pageListOrders = orderDao.getPageListOrders(pageSize, offset, account);
        for (OrderResponse orderResponse : pageListOrders) {
            orderResponse.setStrOrderTime(DateFormatUtil.longToString(orderResponse.getOrderTime()));
        }
        return pageListOrders;
    }

    @Override
    public Integer selectOrderCounts(String account) {
        return orderDao.selectOrderCounts(account);
    }

}
