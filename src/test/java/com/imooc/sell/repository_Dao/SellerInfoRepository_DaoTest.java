package com.imooc.sell.repository_Dao;


import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepository_DaoTest {

    @Autowired
    private SellerInfoRepository_Dao sellerInfoRepository_dao;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");
        SellerInfo result = sellerInfoRepository_dao.save(sellerInfo);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByOpenid(){
        //SellerInfo sellerInfo = sellerInfoRepository_dao.findByOpenid("abc");
       // Assert.assertEquals("abc",sellerInfo.getOpenid());//12-3
    }
}