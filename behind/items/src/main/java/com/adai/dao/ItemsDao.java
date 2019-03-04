package com.adai.dao;

import com.adai.vo.response.IndexItemsResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author Adai
 * create 2019-03-04  11:07
 */
public interface ItemsDao {

    List<IndexItemsResponse> getPageListIndexItems(Integer pageSize, Integer offset);

    Integer selectItemsCounts();

    List<IndexItemsResponse> getPageListSearchItems(Integer pageSize, Integer currentPage, @Param("keyWords") String[] keyWords);

    Integer searchItemsCounts(@Param("keyWords") String[] keyWords);
}
