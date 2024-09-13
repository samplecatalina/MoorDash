package com.moordash.config;

import com.moordash.properties.AliOssProperties;
import com.moordash.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for creating AliOssUtil objects
 */
@Configuration
@Slf4j
public class OssConfiguration {

    @Bean
    @ConditionalOnMissingBean // Make sure the Spring containers have and only have one instance of AliOssUtil
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){
        log.info("Start creating AliOss file uploading util object: {}",aliOssProperties);
        return new AliOssUtil(aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName());
    }
}
