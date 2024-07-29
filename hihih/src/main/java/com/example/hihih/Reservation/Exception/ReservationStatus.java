package com.example.hihih.Reservation.Exception;


import lombok.Getter;

@Getter
public enum ReservationStatus {

    ExistByReservation(400,"예약된 사용자가 있습니다."),
    TimesByOutReservation(400,"날짜가 이전날짜 입니다.");


    private final int status;

    private final String message;

    ReservationStatus(int status,String message){
        this.status=status;
        this.message=message;

    }
}
