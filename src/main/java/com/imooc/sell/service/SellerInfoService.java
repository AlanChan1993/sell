package com.imooc.sell.service;

import com.imooc.sell.dataobject.SellerInfo;

public interface SellerInfoService {
    SellerInfo findSellerInfoByOpenid(String openid);

}
