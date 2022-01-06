package com.imooc.sell.repository_Dao;

import com.imooc.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository_Dao extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openid);
}
