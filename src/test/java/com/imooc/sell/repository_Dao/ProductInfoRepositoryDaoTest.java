package com.imooc.sell.repository_Dao;

import com.imooc.sell.dataobject.ProductInfo;
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
public class ProductInfoRepositoryDaoTest {

    @Autowired
    private ProductInfoRepositoryDao productInfoRepositoryDao;

    @Test
    public void saveTest(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("畅销粥");
        productInfo.setProductIcon("F:/cx/wall/4c06b23610688678b40d3b0c44405440_ChMkJ13FB-OIRwVTAAaoGiez1aUAAvG_wFJkXEABqgy881.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        ProductInfo result = productInfoRepositoryDao.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() throws Exception{
        List<ProductInfo> productInfoList = productInfoRepositoryDao.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());
    }
}