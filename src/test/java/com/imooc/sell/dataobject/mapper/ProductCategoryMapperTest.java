package com.imooc.sell.dataobject.mapper;

import com.imooc.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {
    @Autowired(required = false)
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {
        Map<String,Object>  map =new HashMap<>();
        map.put("category_name","师兄有点不爱");
        map.put("category_type", 111);
        //int result = mapper.insertByMap(map);
        //Assert.assertEquals(1,result);
    }

    @Test
    public void insertByObject(){
        ProductCategory productCategory=new ProductCategory();
    }
    @Test
    public  void findByCategoryType(){
        ProductCategory productCategory = mapper.findByCategoryType(110);
        Assert.assertNotNull(productCategory);

    }
    @Test
    public void updateByCategoryType(){
        int productCategory=mapper.updateByCategoryType("师兄最爱001",110);
        Assert.assertEquals(1,productCategory);
    }

    @Test
    public void selectByCategoryType(){
        ProductCategory result=mapper.selectByCategoryType(110);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateByCategoryId(){
        int result=mapper.updateByCategoryId("师姐爱",105);
        Assert.assertNotNull(result);
    }
}