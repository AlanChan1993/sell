package com.imooc.sell.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnums implements CodeEnum{
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消"),
    ;
    private Integer code;

    private String msg;

    OrderStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //实现接口implements CodeEnum就不必使用此方法
/*    public static OrderStatusEnums getOrderStatusEnums(Integer code) {
        for (OrderStatusEnums orderStatusEnums : OrderStatusEnums.values()) {
            if(orderStatusEnums.getCode().equals(code)){
                return orderStatusEnums;
            }
        }
        return null;
    }*/
}
