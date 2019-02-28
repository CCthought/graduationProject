package com.adai.service;

import com.adai.utils.PageResult;
import com.adai.vo.response.book.IndexBookResponse;
import com.adai.vo.response.book.UniqueBook;

import java.util.List;

/**
 * author Adai
 * create 2019-02-26  17:39
 */
public interface IBookService {
    Integer selectBookCounts();

    PageResult<IndexBookResponse> getPageIndexBook(Integer pageSize, Integer currentPage);
    List<IndexBookResponse> getPageListIndexBook(Integer pageSize, Integer offset);

    UniqueBook getUniqueBook(Integer id);
}
