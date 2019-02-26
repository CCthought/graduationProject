package com.adai.controller;

import com.adai.service.IClothesService;
import com.adai.utils.ActionResponse;
import com.adai.vo.reponse.IndexClothesResponse;
import com.adai.vo.reponse.UniqueClothes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author Adai
 * create 2019-02-21  22:25
 */
@RestController
@CrossOrigin
public class ClothesController {
    @Autowired
    private IClothesService clothesService;

    /**
     * 以pageSize currentPage 来获取衣服数据
     *
     * @param pageSize    每一页数据量
     * @param currentPage 当前页
     * @return .
     */
    @RequestMapping(value = "getPageIndexClothes", method = RequestMethod.GET)
    private ActionResponse getPageIndexClothes(Integer pageSize, Integer currentPage) {
        return ActionResponse.success(clothesService.getPageIndexClothes(pageSize, currentPage));
    }

    @RequestMapping(value = "getUniqueClothes", method = RequestMethod.GET)
    private ActionResponse getUniqueClothes(Integer id) {
        UniqueClothes uniqueClothes = clothesService.getUniqueClothes(id);
        return ActionResponse.success(uniqueClothes);
    }
}
