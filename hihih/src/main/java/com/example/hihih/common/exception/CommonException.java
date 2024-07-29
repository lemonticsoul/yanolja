package com.example.hihih.common.exception;



import com.example.hihih.Member.Dto.MemberResponse;
import com.example.hihih.Member.Exception.NotFoundMemberByUsernameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonException {


    @ExceptionHandler(NotFoundMemberByUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handler(NotFoundMemberByUsernameException e){

        MemberResponse response = MemberResponse.builder()
                .status(e.getStatus())
                .message(e.getMessage())
                .data(e.getUsername())
                .build();

        return ResponseEntity.badRequest().body(response);

    }
}
