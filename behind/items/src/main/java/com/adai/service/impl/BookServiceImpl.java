package com.adai.service.impl;

import com.adai.dao.BookDao;
import com.adai.service.IBookService;
import com.adai.utils.PageResult;
import com.adai.vo.response.book.IndexBookResponse;
import com.adai.vo.response.book.UniqueBook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * author Adai
 * create 2019-02-26  17:39
 */
@Service
public class BookServiceImpl implements IBookService {

    @Resource
    private BookDao bookDao;

    @Override
    public Integer selectBookCounts() {
        return bookDao.selectBookCounts();
    }

    @Override
    public PageResult<IndexBookResponse> getPageIndexBook(Integer pageSize, Integer currentPage) {
        return new PageResult<>(pageSize, this.selectBookCounts(), currentPage, this.getPageListIndexBook(pageSize, currentPage));
    }

    @Override
    public List<IndexBookResponse> getPageListIndexBook(Integer pageSize, Integer offset) {
        offset = (offset - 1) * pageSize; //传递过来的offset是currentPage  需要转换 因为xml offset后面不能运算
        return bookDao.getPageListIndexBook(pageSize,offset);
    }

    @Override
    public UniqueBook getUniqueBook(Integer id) {
        return bookDao.getUniqueBook(id);
    }
}
