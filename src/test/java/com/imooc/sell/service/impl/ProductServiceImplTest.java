package com.imooc.sell.service.impl;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("1");
        Assert.assertEquals("1",productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> list = productService.findUpAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findAll() {
        //PageRequest requst= new PageRequest(0,2);//已过时
        PageRequest requst=PageRequest.of(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(requst);
//        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        for(int i=3;i<30;i++) {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductId(""+i);
            productInfo.setProductName("芒果冰"+i);
            productInfo.setProductPrice(new BigDecimal(3.2));
            productInfo.setProductStock(100);
            productInfo.setProductDescription("新鲜的芒果");
            productInfo.setProductIcon("http://127.0.0.1/sell/static/img/10.jpg");
            productInfo.setProductStatus(ProductStatusEnum.down.getCode());
            productInfo.setCategoryType(3);
            productService.save(productInfo);
        }
    }
}