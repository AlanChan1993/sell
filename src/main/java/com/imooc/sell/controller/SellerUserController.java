package com.imooc.sell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @GetMapping("/login")
    public void login() {
        //1.

    }

    @GetMapping("/logout")
    public void logout(){

    }
}
