package com.imooc.sell.dataobject;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/*
* 类目
* 2020-02-10*/
@Entity
@Proxy(lazy = false)
@DynamicUpdate//动态同步/有时间属性需要一起update的时候使用
@Data//pom.xml引入，代替get,set，toString方法
public class ProductCategory {
    /*类目Id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 设置主键自增
    private Integer categoryId;
    /*类目名字*/
    private  String categoryName;
    /*类目编号*/
    private  Integer categoryType;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

}
