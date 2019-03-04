package com.adai.service.impl;

import com.adai.dao.ItemsDao;
import com.adai.service.IItemsService;
import com.adai.utils.PageResult;
import com.adai.vo.response.IndexItemsResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * author Adai
 * create 2019-03-04  11:12
 */
@Service
public class ItemsServiceImpl implements IItemsService {

    @Resource
    private ItemsDao itemsDao;

    @Override
    public PageResult<IndexItemsResponse> getPageIndexItems(Integer pageSize, Integer currentPage) {
        return new PageResult<>(pageSize,this.selectItemsCounts(),currentPage,
                this.getPageListIndexItems(pageSize,(currentPage - 1) * pageSize));
    }

    @Override
    public List<IndexItemsResponse> getPageListIndexItems(Integer pageSize, Integer offset) {

        return itemsDao.getPageListIndexItems(pageSize,offset);
    }

    @Override
    public Integer selectItemsCounts() {
        return itemsDao.selectItemsCounts();
    }

    @Override
    public List<IndexItemsResponse> getPageListSearchItems(Integer pageSize, Integer currentPage, String[] keyWords) {
        return itemsDao.getPageListSearchItems(pageSize,currentPage,keyWords);
    }

    @Override
    public PageResult<IndexItemsResponse> getPageSearchItems(Integer pageSize, Integer currentPage, String[] keyWords) {
        return new PageResult<>(pageSize,this.searchItemsCounts(keyWords),currentPage,
                this.getPageListSearchItems(pageSize,(currentPage - 1) * pageSize, keyWords));
    }

    @Override
    public Integer searchItemsCounts(String[] keyWords) {
        return itemsDao.searchItemsCounts(keyWords);
    }

}
