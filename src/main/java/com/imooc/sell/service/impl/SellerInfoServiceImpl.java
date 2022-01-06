package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.SellerInfo;
import com.imooc.sell.repository_Dao.SellerInfoRepository_Dao;
import com.imooc.sell.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerInfoServiceImpl implements SellerInfoService {
    @Autowired
    private SellerInfoRepository_Dao repository_dao;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository_dao.findByOpenid(openid);
    }
}
