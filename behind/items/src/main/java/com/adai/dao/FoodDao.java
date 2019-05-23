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
     *
     * @return 总数量
     */
    Integer selectFoodCounts();

    /**
     * 食物集合
     *
     * @param pageSize 每一页数据量
     * @param offset   从哪里开始分页
     * @return 数据集合
     */
    List<IndexFoodResponse> getPageListIndexFood(Integer pageSize, Integer offset);

    /**
     * 取出指定id的Food
     *
     * @param id 数据库唯一ID
     * @return 食物实体
     */
    UniqueFood getUniqueFood(Integer id);
}
