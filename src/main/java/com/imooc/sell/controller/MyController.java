package com.imooc.sell.controller;

import com.imooc.sell.dataobject.MyTest;
import com.imooc.sell.utils.NatReturnResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/")
public class MyController {

    @GetMapping("/index")
    public String natappTest(){
        return NatReturnResultUtil.natTest();
    }

    @GetMapping("/alantest")
    public ModelAndView addobject() {
        MyTest myTest=new MyTest();
        myTest.setName("Alan");
        myTest.setJob("无业");
        myTest.setSex("男");
        Map<String,Object> map = null;
        map.put("myTest",myTest);
        return new ModelAndView("order/abctest",map);

    }
}
