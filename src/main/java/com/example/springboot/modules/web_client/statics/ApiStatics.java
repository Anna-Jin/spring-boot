package com.example.springboot.modules.web_client.statics;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "statics")
@Getter
@Setter
public class ApiStatics {
    private GoogleChat googleChat;
    private Slack slack;

    @Getter
    @Setter
    public static class GoogleChat {
        private String url;
    }

    @Getter
    @Setter
    public static class Slack {
        private String url;
    }
}
