package com.example.springboot.modules.google_chat.builder;


import com.example.springboot.modules.google_chat.builder.card.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WebHookMessageBuilder {

    /**
     * Google Chat WebHook 메세지 생성
     *
     * <br>참고 : <a href="https://developers.google.com/chat/ui?hl=ko">Google Chat 카드 기반 UI 가이드</a>
     *
     * @param title         card header 타이틀
     * @param messageHeader card body 상단 메세지
     * @param message       card body 하단 메세지, 복수 메세지 - divider 추가
     * @return {@link WebHook}
     */
    public static WebHook createGoogleChatMultipleMessage(String title, String messageHeader, List<String> message) {
        // message 에 divider 추가 하는 로직
        List<Object> widgets = new ArrayList<>();
        for (int i = 0; i < message.size(); i++) {
            widgets.add(Widget.builder()
                    .textParagraph(new TextParagraph(message.get(i)))
                    .build());

            // 마지막 줄 divider 제외
            if (message.size() > 1 && i != message.size() - 1) {
                widgets.add(Widget.builder()
                        .divider(new Divider())
                        .build());
            }
        }

        return returnMessage(title, messageHeader, widgets);
    }


    /**
     * Google Chat WebHook 메세지 생성
     *
     * <br>참고 : <a href="https://developers.google.com/chat/ui?hl=ko">Google Chat 카드 기반 UI 가이드</a>
     *
     * @param title         card header 타이틀
     * @param messageHeader card body 상단 메세지
     * @param message       card body 하단 메세지, 단일 메세지
     * @return {@link WebHook}
     */
    public static WebHook createGoogleChatSingleMessage(String title, String messageHeader, String message) {
        // message 에 divider 추가 하는 로직
        List<Object> widgets = new ArrayList<>();
        widgets.add(Widget.builder()
                .textParagraph(new TextParagraph(message))
                .build());


        return returnMessage(title, messageHeader, widgets);
    }

    private static WebHook returnMessage(String title, String messageHeader, List<Object> widgets) {
        Section section = Section.builder()
                .header(messageHeader + "\n\n")
                .widgets(widgets)
                .build();

        Header header = Header.builder()
                .title(title)
                .subtitle(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

        Card card = Card.builder()
                .header(header)
                .sections(new ArrayList<>(Collections.singletonList(section)))
                .build();

        CardsV2 cardsV2 = CardsV2.builder()
                .cardId("webhook_card_ui")
                .card(card)
                .build();

        return WebHook.builder()
                .text("[" + title + "]") // google chat 알람이 card 형식 일 경우 내용이 보이지 않아서 text 형태의 title 추가
                .cardsV2(new ArrayList<>(Collections.singletonList(cardsV2)))
                .build();
    }
}
