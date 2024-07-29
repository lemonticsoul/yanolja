package com.example.hihih.common.handler;

import com.example.hihih.Member.Exception.MemberStatus;
import com.example.hihih.common.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class SigninFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(MemberStatus.NOT_FOUND_MEMBER_BY_ID.getStatus())
                .message(MemberStatus.NOT_FOUND_MEMBER_BY_ID.getMessage())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        objectMapper.writeValue(response.getWriter(), errorResponse);

    }
}
