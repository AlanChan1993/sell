package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;

/*买家*/
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderid);

    //取消订单
    OrderDTO cancleOrder(String openid,String orderid);

}
