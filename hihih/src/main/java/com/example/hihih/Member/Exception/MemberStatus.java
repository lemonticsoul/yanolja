package com.example.hihih.Member.Exception;

import lombok.Getter;

@Getter
public enum MemberStatus {

    Duplicate_Nickname(400,"중복된 닉네임입니다."),
    Duplicate_USERNAME(400,"중복된 아이디입니다."),
    NotFoundMember(400,"멤버를 못 찾았습니다."),
    NOT_FOUND_MEMBER_BY_ID(400,"멤버 id를 못찾았습니다.");


    private final int status;
    private final String message;

    MemberStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
