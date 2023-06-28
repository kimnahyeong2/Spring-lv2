package com.sparta.springlv2.dto;

import jakarta.validation.constraints.*;
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
        @Size(min = 4, max = 10)
        @Pattern(regexp = "^[a-z0-9]*$")
        @NotBlank
        private String username;

        @Size(min = 8, max = 15)
        @Pattern(regexp = "^[a-zA-Z0-9]*$")
        @NotBlank
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class UserInfoDto {
        String username;
    }
}
