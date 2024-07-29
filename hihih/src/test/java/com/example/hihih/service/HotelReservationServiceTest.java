package com.example.hihih.service;

import com.example.hihih.Hotel.Service.HotelService;
import com.example.hihih.Hotel.domain.Hotel;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.is;


@SpringBootTest

class HotelReservationServiceTest {

    @Mock
    Hotel hotel;

    @Autowired
    HotelService hotelService;


    @Test
    public void tripServiceTest(){


    }


}