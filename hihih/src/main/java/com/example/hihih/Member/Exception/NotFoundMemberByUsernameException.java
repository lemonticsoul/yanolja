package com.example.hihih.Member.Exception;


import lombok.Getter;

@Getter
public class NotFoundMemberByUsernameException extends MemberException{

    private String username;
    public NotFoundMemberByUsernameException(String username){
        super(MemberStatus.NotFoundMember);
        this.username=username;
    }

}
