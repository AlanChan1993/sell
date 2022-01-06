package com.imooc.sell.dataobject;

import com.imooc.sell.enums.OrderStatusEnums;
import com.imooc.sell.enums.PayStatusEnums;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;


/*2020-2-16*/
@Entity
@Data
@DynamicUpdate
@Proxy(lazy = false)
public class OrderMaster {

    /*订单Id*/
    @Id //主键的注解
    private String orderId;

    /*买家名字*/
    private String buyerName;

    /*买家手机号*/
    private String buyerPhone;

    /*买家地址*/
    private String buyerAddress;

    /*买家微信Id*/
    private String buyerOpenid;

    /*订单总金额*/
    private BigDecimal orderAmount;

    /*订单状态,默认为新下单*/
    private Integer orderStatus = OrderStatusEnums.NEW.getCode();

    /*支付状态，默认为未支付*/
    private Integer payStatus = PayStatusEnums.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

   /* @Transient//数据找对应字段时会忽略掉
    private List<OrderDetail> orderDetailList;*/

}
