package com.adai.service.impl;

import com.adai.dao.ClothesDao;
import com.adai.service.IClothesService;
import com.adai.utils.PageResult;
import com.adai.vo.response.clothes.IndexClothesResponse;
import com.adai.vo.response.clothes.UniqueClothes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * author Adai
 * create 2019-02-21  22:24
 */
@Service
public class ClothesServiceImpl implements IClothesService {

    @Resource
    private ClothesDao clothesdao;

    @Override
    public Integer selectClothesCounts() {
        return clothesdao.selectClothesCounts();
    }

    @Override
    public PageResult<IndexClothesResponse> getPageIndexClothes(Integer pageSize, Integer currentPage) {
        return new PageResult<>(pageSize, this.selectClothesCounts(), currentPage, this.getPageListIndexClothes(pageSize, currentPage));
    }

    @Override
    public List<IndexClothesResponse> getPageListIndexClothes(Integer pageSize, Integer offset) {
        offset = (offset - 1) * pageSize; //传递过来的offset是currentPage  需要转换 因为xml offset后面不能运算
        return clothesdao.getPageListIndexClothes(pageSize, offset);
    }

    @Override
    public UniqueClothes getUniqueClothes(Integer id) {
        UniqueClothes uniqueClothes = clothesdao.getUniqueClothes(id);
        // 将数组转换成List集合
        String[] colorArra = uniqueClothes.getColorTemp().split(";");
        uniqueClothes.setColor(Arrays.asList(colorArra));
        //因为 size数组从xml映射出来是String 类型 而里面的数据都是Integer类型
        String[] sizeStrArray = uniqueClothes.getSizeTemp().split(";");
        Integer[] sizeIntArray = new Integer[sizeStrArray.length];
        for (int i = 0; i < sizeStrArray.length; i++) {
            sizeIntArray[i] = Integer.parseInt(sizeStrArray[i]);
        }
        uniqueClothes.setSize(Arrays.asList(sizeIntArray));
        return uniqueClothes;
    }
}
