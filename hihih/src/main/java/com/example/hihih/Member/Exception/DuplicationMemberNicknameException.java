package com.example.hihih.Member.Exception;


import lombok.Getter;

@Getter
public class DuplicationMemberNicknameException extends MemberException{

    private String nickname;
    public DuplicationMemberNicknameException(String nickname){
        super(MemberStatus.Duplicate_Nickname);
        this.nickname=nickname;
    }
}
