package com.imooc.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat",ignoreInvalidFields = true)
public class WechatAccountConfig {
    private String mpAppId;

    private String mpAppSecret;


}
