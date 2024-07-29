package com.example.hihih.common.Jwt;


import com.example.hihih.Member.Repository.MemberRepository;
import io.jsonwebtoken.Header;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    private static final String NO_CHECK_URL = "/api/member/sign-up";
    private static final String NO_CHECK_SIGNIN_URL = "/api/member/sign-in";

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        if (request.getRequestURI().equals(NO_CHECK_URL) || request.getRequestURI().equals(NO_CHECK_SIGNIN_URL)) {
            filterChain.doFilter(request, response);
            return;
        }


        String header=request.getHeader("Authorization");
        String accessToken=null;
        String tokenPrefix="Bearer ";

        if (header !=null && !header.isEmpty() && header.startsWith(tokenPrefix)){
            accessToken=header.substring(tokenPrefix.length());
            System.out.println(jwtService.getUsername(accessToken));
        }
        if(jwtService.validateToken(accessToken)&& SecurityContextHolder.getContext().getAuthentication()==null){
            SecurityContextHolder.getContext().setAuthentication(jwtService.getAuthentication(accessToken));
        }

        filterChain.doFilter(request,response);

    }







}
