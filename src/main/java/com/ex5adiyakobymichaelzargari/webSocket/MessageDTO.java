package com.ex5adiyakobymichaelzargari.webSocket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private String from;
    private String text;

    public MessageDTO(String from, String text) {
        this.from = from;
        this.text = text;
    }
}
