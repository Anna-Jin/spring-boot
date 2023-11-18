package com.example.springboot.modules.web_client;

import com.example.springboot.modules.google_chat.builder.card.WebHook;
import com.example.springboot.modules.web_client.builder.ApiWebClientBuilder;
import com.example.springboot.modules.web_client.statics.ApiStatics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class WebClientConnector {
    private final ApiWebClientBuilder webClientBuilder;
    private final ApiStatics statics;


    /**
     * Google Chat WebHook 메세지 전송
     * @param requestBody {@link WebHook}
     */
    public Mono<String> callGoogleChat(WebHook requestBody) {
        ApiStatics.GoogleChat googleChat = statics.getGoogleChat();

        return webClientBuilder.request()
                .post(googleChat.getUrl(), requestBody)
                .connectSubscribe();
    }
}
