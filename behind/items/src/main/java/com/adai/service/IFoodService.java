package com.adai.service;

import com.adai.utils.PageResult;
import com.adai.vo.response.food.IndexFoodResponse;
import com.adai.vo.response.food.UniqueFood;

import java.util.List;

/**
 * author Adai
 * create 2019-02-26  17:39
 */
public interface IFoodService {
    Integer selectFoodCounts();

    PageResult<IndexFoodResponse> getPageIndexFood(Integer pageSize, Integer currentPage);
    List<IndexFoodResponse> getPageListIndexFood(Integer pageSize, Integer offset);

    UniqueFood getUniqueFood(Integer id);
}
