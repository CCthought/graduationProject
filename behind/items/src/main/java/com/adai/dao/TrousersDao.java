package com.adai.dao;

import com.adai.vo.response.trousers.IndexTrousersResponse;
import com.adai.vo.response.trousers.UniqueTrousers;

import java.util.List;

/**
 * author Adai
 * create 2019-02-21  22:08
 */
public interface TrousersDao {
    /**
     * 查询总数量
     * @return 总数量
     */
    Integer selectTrousersCounts();

    /**
     * 分页查询 返回实体数据
     *
     * @param pageSize 每一页数据量
     * @param offset 从哪里开始分页
     * @return 数据集合
     */
    List<IndexTrousersResponse> getPageListIndexTrousers(Integer pageSize, Integer offset);

    /**
     * 取出指定id的trousers
     *
     * @param id 数据库唯一ID
     * @return 裤子实体
     */
    UniqueTrousers getUniqueTrousers(Integer id);

}
