package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.service.SellerInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {
    private static final String openid = "abc";

    @Autowired
    private SellerInfoService sellerInfoService;

    @Test
    public void findSellerInfoByOpenid() throws Exception {
       // SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(openid);
       // Assert.assertEquals(openid, sellerInfo.getOpenid());//12-4
    }
}