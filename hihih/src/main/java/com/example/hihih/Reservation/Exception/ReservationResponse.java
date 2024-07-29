package com.example.hihih.Reservation.Exception;

import com.example.hihih.Hotel.domain.Room;
import com.example.hihih.Member.Dto.MemberResponse;
import com.example.hihih.common.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;


@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse extends CommonResponse {


    private final Object data;

    private final Integer total;


    @Builder
    public ReservationResponse(int status,String message,Object data,Integer total){
        super(status,message);
        this.data=data;
        this.total=total;
    }





//    public static  ReservationResponse ok(Object member) {
//        return new MemberResponse(200, "Success", , null);
//    }
//
//    public static MemberResponse ok(Object member, Integer total) {
//        return new MemberResponse(200, "Success", member, total);
//    }
}
