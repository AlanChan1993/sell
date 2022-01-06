package com.imooc.sell.dataobject.mapper;

import com.imooc.sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface ProductCategoryMapper {
    @Insert("insert into product_category(category_name,category_type) values (#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into product_category(category_name,category_type) values (#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type =#{categoryType}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
    })//查询之后需要将查询结果写出来
    ProductCategory findByCategoryType(Integer categoryType);

    @Update("update product_category set category_name=#{categoryName} where category_type =#{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,@Param("categoryType") Integer categoryType);

    @Delete("delete from product_category where category_type =#{categoryType}" )
    int deleteByCategoryType(Integer categoryType);


    //此sql语句不写在注解，写在配置文件里面
    ProductCategory selectByCategoryType(Integer categoryType);//13-4

    int updateByCategoryId(String categoryName,Integer categoryId);
}
