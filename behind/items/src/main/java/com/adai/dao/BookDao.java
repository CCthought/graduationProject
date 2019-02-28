package com.adai.dao;

import com.adai.vo.response.book.IndexBookResponse;
import com.adai.vo.response.book.UniqueBook;

import java.util.List;

/**
 * author Adai
 * create 2019-02-26  17:29
 */
public interface BookDao {
    /**
     * 查询数量
     * @return .
     */
    Integer selectBookCounts();

    /**
     *
     * @param pageSize 每一页数据量
     * @param offset 从哪里开始分页
     * @return .
     */
    List<IndexBookResponse> getPageListIndexBook(Integer pageSize, Integer offset);

    /**
     * 取出指定id的Book
     *
     * @param id .
     * @return .
     */
    UniqueBook getUniqueBook(Integer id);
}
