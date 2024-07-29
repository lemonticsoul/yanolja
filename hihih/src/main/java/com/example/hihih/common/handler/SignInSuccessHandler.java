package com.example.hihih.common.handler;


import com.example.hihih.Member.Dto.MemberResponse;
import com.example.hihih.Member.Exception.NotFoundMemberByUsernameException;
import com.example.hihih.Member.Repository.MemberRepository;
import com.example.hihih.Member.domain.Member;
import com.example.hihih.common.Jwt.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class SignInSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{

        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        String username=userDetails.getUsername();
        String accessToken=jwtService.createAccessToken(username);
        String refreshToken=jwtService.createRefreshToken();

        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundMemberByUsernameException(username));


        member.updateRefreshToken(refreshToken);

        jwtService.sendAccessAndRefreshToken(response,accessToken,refreshToken);

        Map<String, Object> mem = new HashMap<>();
        mem.put("memId", member.getId());

        ObjectMapper objectMapper = new ObjectMapper();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), MemberResponse.ok(mem));

    }

}
