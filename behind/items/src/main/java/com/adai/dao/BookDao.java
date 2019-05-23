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
     *
     * @return 总数量
     */
    Integer selectBookCounts();

    /**
     * 查询书籍集合
     *
     * @param pageSize 每一页数据量
     * @param offset   从哪里开始分页
     * @return 书籍集合
     */
    List<IndexBookResponse> getPageListIndexBook(Integer pageSize, Integer offset);

    /**
     * 取出指定id的Book
     *
     * @param id 数据库唯一ID
     * @return 该本书实体
     */
    UniqueBook getUniqueBook(Integer id);
}
