package com.adai.controller;

import com.adai.service.IItemsService;
import com.adai.utils.ActionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * author Adai
 * create 2019-02-21  22:25
 */
@RestController
@CrossOrigin
public class ItemsController {
    @Autowired
    private IItemsService itemsService;

    /**
     * 以pageSize currentPage 来获取衣服数据
     *
     * @param pageSize    每一页数据量
     * @param currentPage 当前页
     * @return .
     */
    @RequestMapping(value = "/getPageIndexItems", method = RequestMethod.GET)
    private ActionResponse getPageIndexItems(Integer pageSize, Integer currentPage) {
        return ActionResponse.success(itemsService.getPageIndexItems(pageSize, currentPage));
    }

    /**
     *
     * @param pageSize .
     * @param currentPage .
     * @return .
     */
    @RequestMapping(value = "/getPageSearchItems", method = RequestMethod.GET)
    private ActionResponse getPageSearchItems(Integer pageSize, Integer currentPage,String[] keyWords) {
        System.err.println(Arrays.toString(keyWords));
        System.err.println(Arrays.toString(keyWords));
        System.err.println(Arrays.toString(keyWords));
        return ActionResponse.success(itemsService.getPageSearchItems(pageSize, currentPage, keyWords));
    }
}
