package com.adai.feign;

import com.adai.fallback.CartServiceFallback;
import com.adai.utils.ActionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhouchengpei
 * create 2019-04-27  9:32
 * description
 */
@FeignClient(name = "cart-service",fallback = CartServiceFallback.class)
public interface CartService {

    /**
     * 远程调用购物车方法 删除一条数据
     * @param itemId
     * @param account
     * @return
     */
    @RequestMapping(value = "/deleteCartByAccountAndItemId", method = RequestMethod.GET)
    String deleteCartByAccountAndItemId(@RequestParam("itemId") Integer itemId, @RequestParam("account") String account);

    /**
     * 远程调用购物车方法 删除多条数据
     * @param account
     * @return
     */
    @RequestMapping(value = "/deleteAllCartsByRemote", method = RequestMethod.GET)
    String deleteAllCartsByRemote(String account);
}
