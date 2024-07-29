package com.example.hihih.Hotel.Dto;

import com.example.hihih.Hotel.domain.Room;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class HotelRequestDto {

    private String name;

    private int price;

    private List<Room> room;


}
