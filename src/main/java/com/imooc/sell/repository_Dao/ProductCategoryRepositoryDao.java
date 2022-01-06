package com.imooc.sell.repository_Dao;

import com.imooc.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepositoryDao extends JpaRepository<ProductCategory,Integer> {
     List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
