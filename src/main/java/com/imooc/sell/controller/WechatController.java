package com.imooc.sell.controller;

import com.imooc.sell.enums.ResultEnums;
import com.imooc.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

@RestController
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @Autowired
    private WxMpService service;


    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        WxMpService wxMpService = new WxMpServiceImpl();
        //1.配置/   config的package
        //2.调用方法
        String url="http://sell.natapp4.cc/sell/wechat/userInfo";
        String rediretUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
        return "rediret:" + rediretUrl;
    }

    @GetMapping("/userInfo")
    public String useInfo(@RequestParam("code") String code,
                          @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
       try {
           wxMpOAuth2AccessToken= service.oauth2getAccessToken(code);
       }catch (WxErrorException e){
           log.error("【微信网页授权】 {}", e);
           throw new SellException(ResultEnums.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
       }
        String openid = wxMpOAuth2AccessToken.getOpenId();

        return "rediret:" + returnUrl + "?openid=" + openid;
    }
}
