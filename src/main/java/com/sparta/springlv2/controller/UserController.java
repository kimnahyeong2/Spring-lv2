package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.UserRequestDto;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.security.UserDetailsImpl;
import com.sparta.springlv2.service.UserService;
import com.sparta.springlv2.status.Message;
import com.sparta.springlv2.status.StatusEnum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<Message> signup(@RequestBody @Valid UserRequestDto.SignupRequestDto requestDto, BindingResult bindingResult) {
        Message message = new Message();

        log.info(requestDto.getUsername());
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            message.setStatusCode(404);
            message.setMessage("회원가입에 실패하였습니다");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        User user = userService.signup(requestDto);

        message.setStatusCode(200);
        message.setMessage("회원가입에 성공하였습니다");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // 회원 관련 정보 받기
    @GetMapping("/user-info")
    @ResponseBody
    public UserRequestDto.UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();

        return new UserRequestDto.UserInfoDto(username);
    }
}