package com.adai.service.impl;

import com.adai.dao.CartDao;
import com.adai.service.ICartService;
import com.adai.utils.PageResult;
import com.adai.vo.request.InsertCartRequest;
import com.adai.vo.response.CartResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * author Adai
 * create 2019-02-25  10:34
 */
@Service
public class CartServiceImpl implements ICartService {

    @Resource
    private CartDao cartDao;

    @Override
    public Integer insertCart(InsertCartRequest cart) {
        // 1 代表数据库已经有这个购物车相关信息了 0 代表没有
        Integer isExist = this.isExistCart(cart.getItemId(),cart.getColor(),cart.getSize());
        if (isExist == 0) {
            return cartDao.insertCart(cart);
        } else {
            return this.plusCounts(cart.getItemId(), cart.getCount());
        }
    }

    @Override
    public List<CartResponse> getPageListCarts(Integer pageSize, Integer offset, String account) {
        offset = (offset - 1) * pageSize; // 当前传过来的是 currentPage  需要进行转换
        return cartDao.getPageListCarts(pageSize, offset, account);
    }

    @Override
    public PageResult<CartResponse> getPageCarts(Integer pageSize, Integer offset, String account) {
        return new PageResult<>(pageSize, this.selectCartCounts(account), offset,
                this.getPageListCarts(pageSize, offset, account));
    }

    @Override
    public Integer selectCartCounts(String account) {
        return cartDao.selectCartCounts(account);
    }

    @Override
    public List<CartResponse> selectCartById(Integer id) {
        return cartDao.selectCartById(id);
    }

    @Override
    public Integer isExistCart(Integer itemId,String color, Integer size) {
        return cartDao.isExistCart(itemId, color, size);
    }

    @Override
    public Integer plusCounts(Integer itemId, Integer count) {
        return cartDao.plusCounts(itemId,count);
    }

    @Override
    public Integer changeCount(Integer id, Integer count) {
        return cartDao.changeCount(id,count);
    }

    @Override
    public Integer deleteCartById(Integer id) {
        return cartDao.deleteCartById(id);
    }

    @Override
    public Integer removeAllCarts(String account) {
        return cartDao.removeAllCarts(account);
    }
}
