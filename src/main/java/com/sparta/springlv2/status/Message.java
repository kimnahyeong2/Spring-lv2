package com.sparta.springlv2.status;

import lombok.Data;

@Data
public class Message {

    private int statusCode;
    private String message;
    private Object data;

    public Message() {
        this.statusCode = 200;
        this.message = null;
    }
}