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
     * 查询数量
     * @return .
     */
    Integer selectTrousersCounts();

    /**
     *
     * @param pageSize 每一页数据量
     * @param offset 从哪里开始分页
     * @return .
     */
    List<IndexTrousersResponse> getPageListIndexTrousers(Integer pageSize, Integer offset);

    /**
     * 取出指定id的trousers
     *
     * @param id .
     * @return .
     */
    UniqueTrousers getUniqueTrousers(Integer id);

}
