package com.sparta.springlv2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class UserRequestDto {
    @Setter
    @Getter
    public static class LoginRequestDto {
        private String username;
        private String password;
    }

    @Getter
    @Setter
    public static class SignupRequestDto {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
        @Email
        @NotBlank
        private String email;
    }

    @Getter
    @AllArgsConstructor
    public static class UserInfoDto {
        String username;
    }
}
