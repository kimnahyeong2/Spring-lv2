package com.sparta.springlv2.status;

public enum StatusEnum {

    OK("OK",200);
/*    BAD_REQUEST(400, "BAD_REQUEST"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERER_ERROR(500, "INTERNAL_SERVER_ERROR");*/

    String statusCode;
    int code;

    StatusEnum(String statusCode, int code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}