package com.ls.validator.message;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Message {

    private String language;

    private boolean key;

    private String msg;

    private String value;

    private String field;

    public String parseMsg() {
        return msg.replace("${field}", field).replace("${value}", value);
    }
}
