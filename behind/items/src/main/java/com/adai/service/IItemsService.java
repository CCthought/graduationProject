package com.adai.service;

import com.adai.utils.PageResult;
import com.adai.vo.response.IndexItemsResponse;

import java.util.List;

/**
 * author Adai
 * create 2019-03-04  11:12
 */
public interface IItemsService {
    PageResult<IndexItemsResponse> getPageIndexItems(Integer pageSize, Integer currentPage);
    List<IndexItemsResponse> getPageListIndexItems(Integer pageSize, Integer offset);

    Integer selectItemsCounts();

    List<IndexItemsResponse> getPageListSearchItems(Integer pageSize, Integer currentPage, String[] keyWords);
    PageResult<IndexItemsResponse> getPageSearchItems(Integer pageSize, Integer currentPage, String[] keyWords);
    Integer searchItemsCounts(String[] keyWords);
}
