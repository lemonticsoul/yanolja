package com.example.hihih.Member.Dto;


import com.example.hihih.Member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter

public class MemberSignupDto {

    private String username;

    private String password;

    private String nickname;

    @Builder
    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
    }

}
