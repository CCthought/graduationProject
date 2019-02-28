package com.adai.dao;

import com.adai.vo.response.food.IndexFoodResponse;
import com.adai.vo.response.food.UniqueFood;

import java.util.List;

/**
 * author Adai
 * create 2019-02-26  17:29
 */
public interface FoodDao {
    /**
     * 查询数量
     * @return .
     */
    Integer selectFoodCounts();

    /**
     *
     * @param pageSize 每一页数据量
     * @param offset 从哪里开始分页
     * @return .
     */
    List<IndexFoodResponse> getPageListIndexFood(Integer pageSize, Integer offset);

    /**
     * 取出指定id的Food
     *
     * @param id .
     * @return .
     */
    UniqueFood getUniqueFood(Integer id);
}
