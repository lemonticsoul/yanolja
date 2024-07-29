package com.example.hihih.Hotel.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class Hotel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private String name;


    private int rating;

    private int roomCount;

    private int price;






}
