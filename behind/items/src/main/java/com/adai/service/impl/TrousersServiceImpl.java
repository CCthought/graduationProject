package com.adai.service.impl;

import com.adai.dao.TrousersDao;
import com.adai.service.ITrousersService;
import com.adai.utils.PageResult;
import com.adai.vo.response.trousers.IndexTrousersResponse;
import com.adai.vo.response.trousers.UniqueTrousers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * author Adai
 * create 2019-02-21  22:24
 */
@Service
public class TrousersServiceImpl implements ITrousersService {

    @Resource
    private TrousersDao trousersdao;

    @Override
    public Integer selectTrousersCounts() {
        return trousersdao.selectTrousersCounts();
    }

    @Override
    public PageResult<IndexTrousersResponse> getPageIndexTrousers(Integer pageSize, Integer currentPage) {
        return new PageResult<>(pageSize, this.selectTrousersCounts(), currentPage, this.getPageListIndexTrousers(pageSize, currentPage));
    }

    @Override
    public List<IndexTrousersResponse> getPageListIndexTrousers(Integer pageSize, Integer offset) {
        offset = (offset - 1) * pageSize; //传递过来的offset是currentPage  需要转换 因为xml offset后面不能运算
        return trousersdao.getPageListIndexTrousers(pageSize, offset);
    }

    @Override
    public UniqueTrousers getUniqueTrousers(Integer id) {
        UniqueTrousers uniqueTrousers = trousersdao.getUniqueTrousers(id);
        // 将数组转换成List集合
        String[] colorArra = uniqueTrousers.getColorTemp().split(";");
        uniqueTrousers.setColor(Arrays.asList(colorArra));
        //因为 size数组从xml映射出来是String 类型 而里面的数据都是Integer类型
        String[] sizeStrArray = uniqueTrousers.getSizeTemp().split(";");
        Integer[] sizeIntArray = new Integer[sizeStrArray.length];
        for (int i = 0; i < sizeStrArray.length; i++) {
            sizeIntArray[i] = Integer.parseInt(sizeStrArray[i]);
        }
        uniqueTrousers.setSize(Arrays.asList(sizeIntArray));
        return uniqueTrousers;
    }
}
