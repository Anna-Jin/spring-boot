package com.example.springboot.modules.web_client;

import com.example.springboot.modules.google_chat.builder.card.WebHook;
import com.example.springboot.modules.web_client.builder.ApiWebClientBuilder;
import com.example.springboot.modules.web_client.statics.ApiStatics;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class WebClientConnector {
    private final ApiWebClientBuilder webClientBuilder;
    private final ApiStatics statics;



    public Mono<String> callGoogleChat(WebHook requestBody) {
        ApiStatics.GoogleChat googleChat = statics.getGoogleChat();

        return webClientBuilder.request()
                .post(googleChat.getUrl(), requestBody)
                .connectSubscribe();
    }

}
