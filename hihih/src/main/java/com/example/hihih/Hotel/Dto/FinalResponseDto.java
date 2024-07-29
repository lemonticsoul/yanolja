package com.example.hihih.Hotel.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class FinalResponseDto {

    private int id;

    private String name;

    private int price;

    private List<ResponseRoom> roomList;
}
