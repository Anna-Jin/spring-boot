package com.example.springboot.modules.google_chat.service;

import com.example.springboot._common.exception.errorCode.ResponseCode;
import com.example.springboot._common.response.ApiResponse;
import com.example.springboot.modules.google_chat.builder.WebHookMessageBuilder;
import com.example.springboot.modules.web_client.WebClientConnector;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class GoogleChatService {

    private final WebClientConnector webClientConnector;

    /**
     * 비동기로 Google Chat WebHook 메세지 전송<br>
     * retry 3회 시도
     * @param title         card header 타이틀
     * @param messageHeader card body 상단 메세지
     * @param message       card body 하단 메세지, 복수 메세지 - divider 추가
     */
    public void sendGoogleChatMultipleMessage(String title, String messageHeader, List<String> message) {
        ResponseCode responseCode = ResponseCode.GOOGLE_CHAT_ERROR;

        webClientConnector.callGoogleChat(WebHookMessageBuilder.createGoogleChatMultipleMessage(title, messageHeader, message))
                .flatMap(response -> Mono.just(ResponseEntity.ok().body(ApiResponse.success())))
                .onErrorResume(error -> Mono.just(ResponseEntity.status(responseCode.getStatus()).body(ApiResponse.error(responseCode, "메세지 전송 실패 : " + error.getCause().getMessage()))))
                .subscribe(
                        response -> log.info("메세지 전송 완료 : {}", response),
                        error -> log.error("메세지 전송 실패 : {}", error.getMessage())
                );
    }

    /**
     * 비동기로 Google Chat WebHook 메세지 전송<br>
     * retry 3회 시도
     * @param title         card header 타이틀
     * @param messageHeader card body 상단 메세지
     * @param message       card body 하단 메세지, 단일 메세지
     */
    public void sendGoogleChatMultipleMessage(String title, String messageHeader, String message) {
        ResponseCode responseCode = ResponseCode.GOOGLE_CHAT_ERROR;

        webClientConnector.callGoogleChat(WebHookMessageBuilder.createGoogleChatSingleMessage(title, messageHeader, message))
                .flatMap(response -> Mono.just(ResponseEntity.ok().body(ApiResponse.success())))
                .onErrorResume(error -> Mono.just(ResponseEntity.status(responseCode.getStatus()).body(ApiResponse.error(responseCode, "메세지 전송 실패 : " + error.getCause().getMessage()))))
                .subscribe(
                        response -> log.info("메세지 전송 완료 : {}", response),
                        error -> log.error("메세지 전송 실패 : {}", error.getMessage())
                );
    }
}
