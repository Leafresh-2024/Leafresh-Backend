package com.leafresh.backend;

import com.leafresh.backend.oauth.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class LeafreshBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeafreshBackendApplication.class, args);
    }

}
