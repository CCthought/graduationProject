package com.adai.controller;

import com.adai.service.IBookService;
import com.adai.utils.ActionResponse;
import com.adai.vo.response.book.UniqueBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * author Adai
 * create 2019-02-26  17:28
 */
@RestController
@CrossOrigin
public class BookController {

    @Autowired
    private IBookService bookService;

    /**
     * 以pageSize currentPage 来获取衣服数据
     *
     * @param pageSize    每一页数据量
     * @param currentPage 当前页
     * @return .
     */
    @RequestMapping(value = "getPageIndexBook", method = RequestMethod.GET)
    private ActionResponse getPageIndexBook(Integer pageSize, Integer currentPage) {
        return ActionResponse.success(bookService.getPageIndexBook(pageSize, currentPage));
    }

    /**
     * 通过ID 来唯一确定获得的衣服
     * @param id .
     * @return .
     */
    @RequestMapping(value = "getUniqueBook", method = RequestMethod.GET)
    private ActionResponse getUniqueBook(Integer id) {
        UniqueBook uniqueBook = bookService.getUniqueBook(id);
        return ActionResponse.success(uniqueBook);
    }

}
