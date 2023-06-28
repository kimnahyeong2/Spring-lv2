package com.sparta.springlv2.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String username;
    private String contents;
    private String pwd;
}
