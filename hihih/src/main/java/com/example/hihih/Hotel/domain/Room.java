package com.example.hihih.Hotel.domain;

import com.example.hihih.Hotel.domain.Hotel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;


    private String roomname;

    private int price;

    private int count;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;






}
