package com.adai.service;

import com.adai.utils.PageResult;
import com.adai.vo.response.trousers.IndexTrousersResponse;
import com.adai.vo.response.trousers.UniqueTrousers;

import java.util.List;

/**
 * author Adai
 * create 2019-02-21  22:23
 */
public interface ITrousersService {
    Integer selectTrousersCounts();

    PageResult<IndexTrousersResponse> getPageIndexTrousers(Integer pageSize, Integer currentPage);
    List<IndexTrousersResponse> getPageListIndexTrousers(Integer pageSize, Integer offset);

    UniqueTrousers getUniqueTrousers(Integer id);
}
