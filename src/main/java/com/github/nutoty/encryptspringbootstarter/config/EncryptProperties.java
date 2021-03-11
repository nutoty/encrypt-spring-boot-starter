package com.github.nutoty.encryptspringbootstarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "spring.encrypt")
public class EncryptProperties {

    /**
     * key为16位的字符串
     */
    private static final String DEFAULT_KEY = "www.itboyhub.com";

    private String key = DEFAULT_KEY;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
