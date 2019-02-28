package com.adai.controller;

import com.adai.service.ITrousersService;
import com.adai.utils.ActionResponse;
import com.adai.vo.response.trousers.UniqueTrousers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * author Adai
 * create 2019-02-21  22:25
 */
@RestController
@CrossOrigin
public class TrousersController {
    @Autowired
    private ITrousersService trousersService;

    /**
     * 以pageSize currentPage 来获取衣服数据
     *
     * @param pageSize    每一页数据量
     * @param currentPage 当前页
     * @return .
     */
    @RequestMapping(value = "getPageIndexTrousers", method = RequestMethod.GET)
    private ActionResponse getPageIndexTrousers(Integer pageSize, Integer currentPage) {
        return ActionResponse.success(trousersService.getPageIndexTrousers(pageSize, currentPage));
    }

    /**
     * 通过ID 来唯一确定获得的衣服
     * @param id .
     * @return .
     */
    @RequestMapping(value = "getUniqueTrousers", method = RequestMethod.GET)
    private ActionResponse getUniqueTrousers(Integer id) {
        UniqueTrousers uniqueTrousers = trousersService.getUniqueTrousers(id);
        return ActionResponse.success(uniqueTrousers);
    }
}
