package com.znxsx.cptt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * MyConfig
 *
 * @author xiongshixin
 * @date 2018/10/17
 **/
@Component
@ConfigurationProperties(prefix = "my")
@Data
public class MyConfig {
    private String number;
    private String email;
    private String apiUrl;
    private String key;
}
