package com.adai.controller;

import com.adai.service.IFoodService;
import com.adai.utils.ActionResponse;
import com.adai.vo.response.food.UniqueFood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * author Adai
 * create 2019-02-26  17:28
 */
@RestController
@CrossOrigin
public class FoodController {

    @Autowired
    private IFoodService foodService;

    /**
     * 以pageSize currentPage 来获取衣服数据
     *
     * @param pageSize    每一页数据量
     * @param currentPage 当前页
     * @return .
     */
    @RequestMapping(value = "getPageIndexFood", method = RequestMethod.GET)
    private ActionResponse getPageIndexFood(Integer pageSize, Integer currentPage) {
        return ActionResponse.success(foodService.getPageIndexFood(pageSize, currentPage));
    }

    /**
     * 通过ID 来唯一确定获得的衣服
     * @param id .
     * @return .
     */
    @RequestMapping(value = "getUniqueFood", method = RequestMethod.GET)
    private ActionResponse getUniqueFood(Integer id) {
        UniqueFood uniqueFood = foodService.getUniqueFood(id);
        return ActionResponse.success(uniqueFood);
    }

}
