package com.moordash.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "moordash.jwt")
@Data
public class JwtProperties {

    /**
     * Admin side: configurations for generating jwt token for employees
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

    /**
     * User side: configurations for generating jwt token for WeChat users
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;

}
