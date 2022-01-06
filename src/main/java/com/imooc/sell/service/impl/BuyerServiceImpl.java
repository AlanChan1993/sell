package com.imooc.sell.service.impl;

import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnums;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderid) {
        return checkOwnerOrder(openid, orderid);
    }

    @Override
    public OrderDTO cancleOrder(String openid, String orderid) {
        OrderDTO orderDTO = checkOwnerOrder(openid, orderid);
        if (orderDTO == null) {
            log.error("【取消订单】 查不到该订单,orderid={},orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnums.ORDER_OWNER_ERROR);
        }

        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOwnerOrder(String openid,String orderid){
        OrderDTO orderDTO = orderService.findOne(orderid);
        if (orderDTO == null) {
            return null;
        }

        //判断是否是自己的订单
        if(!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】 订单的哦openid不一致,openid={},orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnums.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
