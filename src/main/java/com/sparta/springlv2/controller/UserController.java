package com.sparta.springlv2.controller;

import com.sparta.springlv2.dto.UserRequestDto;
import com.sparta.springlv2.entity.User;
import com.sparta.springlv2.service.UserService;
import com.sparta.springlv2.status.Message;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            message.setStatusCode(404);
            message.setMessage("회원가입에 실패하였습니다");
            return new ResponseEntity<Message>(message, HttpStatus.NOT_FOUND);
        }

        User user = userService.signup(requestDto);

        message.setMessage("회원가입에 성공하였습니다");

        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }
}