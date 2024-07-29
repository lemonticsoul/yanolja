package com.example.hihih.Reservation.Exception;

import java.time.LocalDate;

public class ExistByReservation extends ReservationException {


    public ExistByReservation(){
        super(ReservationStatus.ExistByReservation);
    }


}
