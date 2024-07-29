package com.example.hihih.Member.Exception;


import lombok.Getter;

@Getter
public class DuplicationMemberUsernameException extends MemberException {

    private String username;


    public DuplicationMemberUsernameException(String username){
        super(MemberStatus.Duplicate_USERNAME);
        this.username=username;
    }
}

