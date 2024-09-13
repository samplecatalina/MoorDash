package com.moordash.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "moordash.wechat")
@Data
public class WeChatProperties {

    private String appid; // mini program appid
    private String secret; // mini program secret
    private String mchid; // merchant id
    private String mchSerialNo; // merchant API license serial number
    private String privateKeyFilePath; // merchant private key file path
    private String apiV3Key; // license decrypt key
    private String weChatPayCertFilePath; // platform certificate file path
    private String notifyUrl; // callback URL after payment success
    private String refundNotifyUrl; // callback URL after refund success

}
