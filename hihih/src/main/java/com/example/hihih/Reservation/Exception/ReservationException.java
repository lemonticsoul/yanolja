package com.example.hihih.Reservation.Exception;

import com.example.hihih.common.response.CommonResponse;

public class ReservationException extends RuntimeException {

    private ReservationStatus reservationStatus;
    public ReservationException(ReservationStatus reservationStatus) {
        this.reservationStatus=reservationStatus;
    }

    public int getStatus() {
        return reservationStatus.getStatus();
    }

    public String getMessage() {
        return reservationStatus.getMessage();
    }

}
