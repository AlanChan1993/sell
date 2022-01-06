package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnums;
import com.imooc.sell.enums.PayStatusEnums;
import com.imooc.sell.enums.ResultEnums;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID="110110";

    private final String ORDER_ID="1582277180147312989";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("高低大街");
        orderDTO.setBuyerName("猪八戒");
        orderDTO.setBuyerPhone("13412345678");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> detailList=new ArrayList<>();
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setProductId("3");
        orderDetail.setProductQuantity(8);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("2");
        orderDetail2.setProductQuantity(6);
        detailList.add(orderDetail);
        detailList.add(orderDetail2);
        orderDTO.setOrderDetailList(detailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】result={}",result);//6-6
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】 result={}",result);
        Assert.assertEquals(ORDER_ID, result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest=PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, pageRequest);
        Assert.assertNotSame(0,orderDTOPage.getTotalElements());

    }

    @Test
    public void cancel() {
        OrderDTO orderDTO2 = orderService.findOne(ORDER_ID);
        //OrderDTO result = orderService.cancel(orderDTO2);
        //Assert.assertEquals(OrderStatusEnums.CANCEL.getCode(), result.getOrderStatus());//6-8
    }

    @Test
    public void finish() {
        OrderDTO orderDTO2 = orderService.findOne(ORDER_ID);
        //OrderDTO result = orderService.finish(orderDTO2);
        //Assert.assertEquals(OrderStatusEnums.FINISHED.getCode(), result.getOrderStatus());//6-9
    }

    @Test
    public void paid() {
        OrderDTO orderDTO2 = orderService.findOne(ORDER_ID);
        //OrderDTO result = orderService.paid(orderDTO2);
        //Assert.assertEquals(PayStatusEnums.SUCCESS.getCode(), result.getPayStatus());//6-9
    }

    @Test
    public void fList(){
        PageRequest pageRequest=PageRequest.of(0,10);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        Assert.assertNotSame(0,orderDTOPage.getTotalElements());//9-2
    }
}