package com.imooc.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/*商品包含类目*/
@Data
public class ProductVO {

    @JsonProperty("name")//把对象序列化返回前端时就是name
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO>  productInfoVOList;
}
