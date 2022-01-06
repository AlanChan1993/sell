package com.imooc.sell.repository_Dao;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryDaoTest {
    @Autowired
    private ProductCategoryRepositoryDao repositoryDao;

    @Test
    public void findOneTest(){
        ProductCategory  productCategory = repositoryDao.getOne(100);
        System.out.println(productCategory.toString());
    }

    @Test
    public  void  saveTest(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("男生榜");
        productCategory.setCategoryType(3);
        //repositoryDao.save(productCategory);
    }

    @Test
    public  void  updateTest(){
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("男生榜");
        productCategory.setCategoryType(8);
        //repositoryDao.save(productCategory);
    }

    @Test
    public void getAndUpdateTest(){
        //ProductCategory productCategory=repositoryDao.getOne(2);
        //productCategory.setCategoryType(8);
        //repositoryDao.save(productCategory);

    }

    @Test
    @Transactional//用于拦截测试数据，不将测试数据插入数据库，完全回滚
    public void modifyTest(){
        ProductCategory productCategory=new ProductCategory("女生最爱",3);
        //ProductCategory result= repositoryDao.save(productCategory);
        //Assert.assertNotNull(result);
        //Assert.assertNotEquals(null,result);//与上面代码意义一致
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list= Arrays.asList(2,3,4);
        List<ProductCategory> result=repositoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}