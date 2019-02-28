package com.adai.dao;

import com.adai.vo.response.clothes.IndexClothesResponse;
import com.adai.vo.response.clothes.UniqueClothes;

import java.util.List;

/**
 * author Adai
 * create 2019-02-21  22:08
 */
public interface ClothesDao {
    /**
     * 查询数量
     * @return .
     */
    Integer selectClothesCounts();

    /**
     *
     * @param pageSize 每一页数据量
     * @param offset 从哪里开始分页
     * @return .
     */
    List<IndexClothesResponse> getPageListIndexClothes(Integer pageSize, Integer offset);

    /**
     * 取出指定id的clothes
     *
     * @param id .
     * @return .
     */
    UniqueClothes getUniqueClothes(Integer id);

}
