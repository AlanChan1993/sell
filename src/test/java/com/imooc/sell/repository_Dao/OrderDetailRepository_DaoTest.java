package com.imooc.sell.repository_Dao;

import com.imooc.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepository_DaoTest {

    @Autowired
    private OrderDetailRepository_Dao orderDetailRepository_dao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("2");
        orderDetail.setOrderId("1111221");
        orderDetail.setProductIcon("http://xxx.cn");
        orderDetail.setProductId("1111112222");
        orderDetail.setProductPrice(new BigDecimal(5.6));
        orderDetail.setProductQuantity(2);
        orderDetail.setProductName("皮虾粥");
        OrderDetail result = orderDetailRepository_dao.save(orderDetail);
        Assert.assertNotNull(result);

    }
    @Test
    public void findByOrderId() throws Exception{
        List<OrderDetail> orderDetails = orderDetailRepository_dao.findByOrderId("1582276802474283683");
        Assert.assertNotEquals(0,orderDetails.size());
    }

}