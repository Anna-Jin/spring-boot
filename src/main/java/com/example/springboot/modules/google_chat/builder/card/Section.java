package com.example.springboot.modules.google_chat.builder.card;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Section {
    private String header;
    @Builder.Default
    private boolean collapsible = true;
    @Builder.Default
    private int uncollapsibleWidgetsCount = 3;
    private List<Object> widgets;
}
