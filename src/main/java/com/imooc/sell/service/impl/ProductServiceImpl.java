package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.enums.ResultEnums;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository_Dao.ProductInfoRepositoryDao;
import com.imooc.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepositoryDao productInfoRepositoryDao;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepositoryDao.getOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepositoryDao.findByProductStatus(ProductStatusEnum.up.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {

        return productInfoRepositoryDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepositoryDao.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepositoryDao.getOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnums.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepositoryDao.save(productInfo);

        }

    }

    @Override
    @Transactional//事务
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepositoryDao.getOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnums.PRODUCT_NOT_EXIST);
            }

            Integer i = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(i<0){
                throw new SellException(ResultEnums.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(i);
            productInfoRepositoryDao.save(productInfo);//6-5
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo=productInfoRepositoryDao.getOne(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnums.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum()==ProductStatusEnum.up){
            throw new SellException(ResultEnums.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.up.getCode());
        return productInfoRepositoryDao.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo=productInfoRepositoryDao.getOne(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnums.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum()==ProductStatusEnum.down){
            throw new SellException(ResultEnums.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.down.getCode());
        return productInfoRepositoryDao.save(productInfo);
    }
}
