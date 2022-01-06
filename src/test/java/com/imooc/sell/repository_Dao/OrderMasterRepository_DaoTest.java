package com.imooc.sell.repository_Dao;


import com.imooc.sell.dataobject.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterRepository_DaoTest {

    @Autowired
    private OrderMasterRepository_Dao orderMasterRepository_dao;

    private final String OPENID="30303030";
    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("3");
        orderMaster.setBuyerName("金大爷");
        orderMaster.setBuyerPhone("13412345678");
        orderMaster.setBuyerAddress("天河躺下");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(3.3));
        OrderMaster result = orderMasterRepository_dao.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception{
        PageRequest request=PageRequest.of(0,1);
        Page<OrderMaster> result = orderMasterRepository_dao.findByBuyerOpenid(OPENID, request);
        //System.out.println("Alan : "+result.getTotalElements()+"   |  "+result.getTotalPages());
        Assert.assertNotNull(result);
    }

    @Test
    public void myTest(){
        List<OrderMaster> orderMasterList=orderMasterRepository_dao.findAll();
        List list=orderMasterList.stream().map(e -> e.getBuyerName()).collect(Collectors.toList());
        //log.info("【123】,list={}",list);
        for (Object o:list){
            //System.out.println("i ="+i+"   "+list.get(i));
            log.info("【o】,o={}",o);
        }
       // list.forEach(e->System.out.println(e));
       // orderMasterList.stream().map(e -> e.getBuyerName()).collect(Collectors.toList()).forEach(System.out::println);
    }
}