package com.imooc.sell.controller;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.converter.OrderForm2OrderDTOConverter;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnums;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】 参数不正确,orderForm={}", orderForm);
            throw new SellException(ResultEnums.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnums.CART_EMPTY);
        }

        OrderDTO createOrder = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderid", createOrder.getOrderId());
        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") String page,
                                         @RequestParam(value = "size", defaultValue = "10") String size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnums.PARAM_ERROR);
        }

        PageRequest requestPage = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, requestPage);

        return ResultVOUtil.success(orderDTOPage.getContent());
        //return ResultVOUtil.success();
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<List<OrderDTO>> detail(@RequestParam("openid") String openid,
                                           @RequestParam("orderid") String orderid) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderid);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancle")
    public ResultVO<List<OrderDTO>> cancleDo(@RequestParam("openid") String openid,
                                             @RequestParam("orderid") String orderid) {
        buyerService.cancleOrder(openid,orderid);
        return ResultVOUtil.success();//6-12、6-13
    }
}
