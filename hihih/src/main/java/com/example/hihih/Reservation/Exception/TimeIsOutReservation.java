package com.example.hihih.Reservation.Exception;

public class TimeIsOutReservation extends ReservationException{

    public TimeIsOutReservation(){
        super(ReservationStatus.TimesByOutReservation);

    }


}
