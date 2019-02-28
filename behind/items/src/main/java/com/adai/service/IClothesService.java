package com.adai.service;

import com.adai.utils.PageResult;
import com.adai.vo.response.clothes.IndexClothesResponse;
import com.adai.vo.response.clothes.UniqueClothes;

import java.util.List;

/**
 * author Adai
 * create 2019-02-21  22:23
 */
public interface IClothesService {
    Integer selectClothesCounts();

    PageResult<IndexClothesResponse> getPageIndexClothes(Integer pageSize, Integer currentPage);
    List<IndexClothesResponse> getPageListIndexClothes(Integer pageSize, Integer offset);

    UniqueClothes getUniqueClothes(Integer id);
}
