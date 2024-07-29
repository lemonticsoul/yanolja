package com.example.hihih.Reservation.dto;


import com.example.hihih.Hotel.domain.Room;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReservationReqDto {

    private String hotelname;

    private String roomname;

    private int roomcost;

    private LocalDate startDate;

    private LocalDate endDate;

}
