package com.adai.service.impl;

import com.adai.dao.FoodDao;
import com.adai.service.IFoodService;
import com.adai.utils.PageResult;
import com.adai.vo.response.food.IndexFoodResponse;
import com.adai.vo.response.food.UniqueFood;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * author Adai
 * create 2019-02-26  17:39
 */
@Service
public class FoodServiceImpl implements IFoodService {

    @Resource
    private FoodDao foodDao;

    @Override
    public Integer selectFoodCounts() {
        return foodDao.selectFoodCounts();
    }

    @Override
    public PageResult<IndexFoodResponse> getPageIndexFood(Integer pageSize, Integer currentPage) {
        return new PageResult<>(pageSize, this.selectFoodCounts(), currentPage, this.getPageListIndexFood(pageSize, currentPage));
    }

    @Override
    public List<IndexFoodResponse> getPageListIndexFood(Integer pageSize, Integer offset) {
        offset = (offset - 1) * pageSize; //传递过来的offset是currentPage  需要转换 因为xml offset后面不能运算
        return foodDao.getPageListIndexFood(pageSize,offset);
    }

    @Override
    public UniqueFood getUniqueFood(Integer id) {
        return foodDao.getUniqueFood(id);
    }
}
