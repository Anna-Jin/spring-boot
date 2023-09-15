package com.example.springboot.modules.google_chat.builder.card;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Widget {
    private TextParagraph textParagraph;
    private Divider divider;
}
