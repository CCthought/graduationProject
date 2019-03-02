package com.adai.service.impl;

import com.adai.dao.CartDao;
import com.adai.service.ICartService;
import com.adai.utils.PageResult;
import com.adai.vo.request.InsertCartRequest;
import com.adai.vo.response.CartResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
        Integer isExist;
        // 判断购物车属于那个类别 是否有 color size
        if (cart.getCount() != null && cart.getSize() != null) {
            // 1 代表数据库已经有这个购物车相关信息了 0 代表没有
            isExist = this.isExistCartHasColorSize(cart.getItemId(), cart.getColor(), cart.getSize(),cart.getAccount());
        } else {
            // 1 代表数据库已经有这个购物车相关信息了 0 代表没有
            isExist = this.isExistCartNoColorSize(cart.getItemId(),cart.getAccount());
        }
        if (isExist == 0) {
            return cartDao.insertCart(cart);
        } else {
            return this.plusCounts(cart.getItemId(), cart.getCount(),cart.getAccount());
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
    public Integer isExistCartHasColorSize(Integer itemId, String color, Integer size,String account) {
        return cartDao.isExistCartHasColorSize(itemId, color, size,account);
    }

    @Override
    public Integer isExistCartNoColorSize(Integer itemId,String account) {
        return cartDao.isExistCartNoColorSize(itemId,account);
    }

    @Override
    public Integer plusCounts(Integer itemId, Integer count,String account) {
        return cartDao.plusCounts(itemId, count,account);
    }

    @Override
    public Integer changeCount(Integer id, Integer count) {
        return cartDao.changeCount(id, count);
    }

    @Override
    public Integer deleteCartById(Integer id) {
        return cartDao.deleteCartById(id);
    }

    @Override
    public Integer removeAllCarts(String account) {
        return cartDao.removeAllCarts(account);
    }

    @Override
    public Integer getAllMoney(String account) {
        Integer totalMoney = 0;
        Integer price = -1;
        Integer count = -1;
        List<Map<String, Integer>> allMoney = cartDao.getAllMoney(account);
        for (Map<String, Integer> map : allMoney) {
            for (Map.Entry<String, Integer> entey : map.entrySet()) {
                String key = entey.getKey();
                if ("price".equals(key)) {
                    price = entey.getValue();
                }else {
                    count = entey.getValue();
                }
                if (price != -1 && count != -1) {
                    totalMoney = totalMoney + (price * count);
                    price = -1;
                    count = -1;
                }
            }
        }
        return totalMoney;
    }

    @Override
    public List<CartResponse> getAllCarts(String account) {
        return cartDao.getAllCarts(account);
    }

    @Override
    public Integer deleteAllCarts(String account) {
        return cartDao.deleteAllCarts(account);
    }
}
