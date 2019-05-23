package com.adai.dao;

import com.adai.vo.response.IndexItemsResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author Adai
 * create 2019-03-04  11:07
 */
public interface ItemsDao {

    /**
     * 分页查询
     *
     * @param pageSize 分页大小
     * @param offset   定位
     * @return 数据集合
     */
    List<IndexItemsResponse> getPageListIndexItems(Integer pageSize, Integer offset);

    /**
     * 查询总数量
     *
     * @return 总数量
     */
    Integer selectItemsCounts();

    /**
     * 模糊查询 返回实体数据
     *
     * @param pageSize    分页大小
     * @param currentPage 当前页
     * @param keyWords    关键词
     * @return 数据集合
     */
    List<IndexItemsResponse> getPageListSearchItems(Integer pageSize, Integer currentPage, @Param("keyWords") String[] keyWords);

    /**
     * 模糊查询 返回总数量
     *
     * @param keyWords
     * @return 总数量
     */
    Integer searchItemsCounts(@Param("keyWords") String[] keyWords);
}
