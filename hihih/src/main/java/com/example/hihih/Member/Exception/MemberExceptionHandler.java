package com.example.hihih.Member.Exception;

import com.example.hihih.Member.Controller.MemberController;
import com.example.hihih.Member.Dto.MemberResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;


@RestControllerAdvice(basePackageClasses = MemberController.class)
public class MemberExceptionHandler {


    @ExceptionHandler(DuplicationMemberNicknameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<MemberResponse> handler(DuplicationMemberNicknameException e) {

        MemberResponse response = MemberResponse.builder()
                .status(e.getStatus())
                .message(e.getMessage())
                .data(e.getNickname())
                .build();


        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DuplicationMemberUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<MemberResponse> handler(DuplicationMemberUsernameException e) {

        MemberResponse response = MemberResponse.builder()
                .status(e.getStatus())
                .message(e.getMessage())
                .data(e.getUsername())
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotFoundMemberByUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<MemberResponse> handler(NotFoundMemberByUsernameException e) {

        MemberResponse response = MemberResponse.builder()
                .status(e.getStatus())
                .message(e.getMessage())
                .data(e.getUsername())
                .build();

        return ResponseEntity.badRequest().body(response);
    }

//    @ExceptionHandler(DuplicationNicknameException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    private ResponseEntity<MemberResponse> handler(DuplicationNicknameException e) {
//
//        MemberResponse response = MemberResponse.builder()
//                .status(e.getStatus())
//                .message(e.getMessage())
//                .data(e.getNickname())
//                .build();
//
//        return ResponseEntity.badRequest().body(response);
//    }
//
//    @ExceptionHandler(NotEqualPasswordException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    private ResponseEntity<MemberResponse> handler(NotEqualPasswordException e) {
//
//        MemberResponse response = MemberResponse.builder()
//                .status(e.getStatus())
//                .message(e.getMessage())
//                .build();
//
//        return ResponseEntity.badRequest().body(response);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    private ResponseEntity<MemberResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        Map<String, Object> errors = new HashMap<>();
//        for (FieldError error : e.getFieldErrors()) {
//            errors.put(error.getField(), error.getDefaultMessage());
//        }
//
//        MemberResponse response = MemberResponse.builder()
//                .status(e.getStatusCode().value())
//                .message("fail")
//                .data(errors)
//                .build();
//
//        return ResponseEntity.badRequest().body(response);
//    }
//
//    @ExceptionHandler(JwtException.class)
//    private ResponseEntity<MemberResponse> jwtRefreshTokenNotValidException(JwtException e) {
//        MemberResponse response = MemberResponse.builder()
//                .status(HttpStatus.UNAUTHORIZED.value())
//                .message("Refresh Token 만료로 인한 재로그인을 시도해주세요.")
//                .build();
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//    }
}
