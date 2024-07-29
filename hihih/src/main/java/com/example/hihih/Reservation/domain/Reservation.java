package com.example.hihih.Reservation.domain;


import com.example.hihih.Hotel.domain.Room;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@Entity
@AllArgsConstructor
public class Reservation {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String username;

    private String hotelname;

    private String roomname;

    private int roomcost;

    private LocalDate startdate;

    private LocalDate enddate;



}
