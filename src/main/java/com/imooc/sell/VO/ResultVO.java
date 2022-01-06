package com.imooc.sell.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
/*http请求返回的最外层对象*/
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)用于前端返回时，不返回值为null的属性
public class ResultVO<T> {
    /*错误码*/
    private Integer code;

    /*提示信息*/
    private String msg;

    /*返回的具体内容*/
    private T data;

}
