package com.example.hihih.Member.Dto;


import com.example.hihih.common.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse extends CommonResponse {

    private final Object data;

    private final Integer total;


    @Builder
    public MemberResponse(int status,String message,Object data,Integer total){
        super(status,message);
        this.data=data;
        this.total=total;
    }

    public static MemberResponse ok(Object member) {
        return new MemberResponse(200, "Success", member, null);
    }

    public static MemberResponse ok(Object member, Integer total) {
        return new MemberResponse(200, "Success", member, total);
    }
}
