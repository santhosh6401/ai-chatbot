package com.chatbot.article.configuration;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomProperties {

    @Value("${config.keys.privateAccessToken}")
    private String privateAccessToken;
}
