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
     *
     * @return 总数量
     */
    Integer selectClothesCounts();

    /**
     * 查询衣服集合
     *
     * @param pageSize 每一页数据量
     * @param offset   从哪里开始分页
     * @return 数据集合
     */
    List<IndexClothesResponse> getPageListIndexClothes(Integer pageSize, Integer offset);

    /**
     * 取出指定id的clothes
     *
     * @param id 数据库唯一ID
     * @return 衣服实体
     */
    UniqueClothes getUniqueClothes(Integer id);

}
