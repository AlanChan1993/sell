package com.imooc.sell.controller;


import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnums;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

/*卖家端订单*/
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellOrderController {
    @Autowired
    private OrderService orderService;

    /*
     * 订单列表
     * 1.page 第几页
     * 2.size 一页有几条数据
     * */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        //log.info("【orderDTOPage】 元素,orderDTOPage={}", orderDTOPage.getTotalElements());
        //log.info("【orderDTOPage】 页数,orderDTOPage={}",orderDTOPage.getTotalPages());
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage",page);
        map.put("currentSize",size);
        return new ModelAndView("order/list", map);//9-2
    }

    @GetMapping("/cancle")
    public ModelAndView cancle(@RequestParam("orderId") String orderId,
                               Map<String ,Object> map){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (Exception e){
            log.error("【卖家端取消订单】发生异常");
            map.put("msg", ResultEnums.ORDER_NOT_EXIST.getMsg());
            map.put("url","sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnums.ORDER_CANCLE_SUCCESS.getMsg());
        map.put("url","sell/seller/order/list");
        return new ModelAndView("common/success");
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (Exception e){
            log.error("【卖家端查询订单详情】发生异常");
            map.put("msg", ResultEnums.ORDER_NOT_EXIST.getMsg());
            map.put("url","sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    @GetMapping("finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String ,Object> map){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (Exception e){
            log.error("【卖家端完结订单】发生异常");
            map.put("msg", ResultEnums.ORDER_NOT_EXIST.getMsg());
            map.put("url","sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnums.ORDER_FINISH_SUCCESS.getMsg());
        map.put("url","sell/seller/order/list");
        return new ModelAndView("common/success");
    }


}
