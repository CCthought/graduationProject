package com.adai.controller;

import com.adai.service.IOrderService;
import com.adai.utils.ActionResponse;
import com.adai.vo.request.InsertOrder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * author Adai
 * create 2019-03-01  13:55
 */
@RestController
@CrossOrigin
public class OrderController {

    @Resource
    private IOrderService orderService;

    @RequestMapping(value = "/insertOrder", method = RequestMethod.POST, consumes = "application/json")
    public ActionResponse insertOrder(@RequestBody InsertOrder order){
        System.err.println(order);
        System.err.println(order);
        System.err.println(order);
        Integer IS_SUCCESS = orderService.insertOrder(order);
        if(IS_SUCCESS == 1){
                return ActionResponse.success();
        } else{
            return new ActionResponse("497","订单插入失败");
        }
    }

    @RequestMapping(value = "/getPageOrders", method = RequestMethod.GET)
    public ActionResponse getPageOrders(Integer pageSize, Integer currentPage, String account){
        return ActionResponse.success(orderService.getPageOrders(pageSize,currentPage,account));
    }

}
