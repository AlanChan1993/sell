package com.imooc.sell.repository_Dao;

import com.imooc.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository_Dao extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderIdIn(List<String> orderIdList);

    List<OrderDetail> findByOrderId(String orderId);
}
