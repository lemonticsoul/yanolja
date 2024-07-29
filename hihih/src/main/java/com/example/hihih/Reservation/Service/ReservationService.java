package com.example.hihih.Reservation.Service;

import com.example.hihih.Hotel.Repsitory.HotelRepository;
import com.example.hihih.Hotel.Repsitory.RoomRepository;
import com.example.hihih.Hotel.domain.Hotel;
import com.example.hihih.Hotel.domain.Room;
import com.example.hihih.Member.Exception.NotFoundMemberByUsernameException;
import com.example.hihih.Reservation.Exception.ExistByReservation;
import com.example.hihih.Reservation.Exception.TimeIsOutReservation;
import com.example.hihih.Reservation.Repository.ReservationRepository;
import com.example.hihih.Reservation.domain.Reservation;
import com.example.hihih.Reservation.dto.ReservationReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public void addReservation(String username, ReservationReqDto reservationReqDto){
        LocalDate startDate =reservationReqDto.getStartDate();

        log.info("유저정보"+username);

        if (username==null){
            throw new NotFoundMemberByUsernameException(username);
        }

        LocalDate endDate=reservationReqDto.getEndDate();
        if (reservationRepository.findByReservation(startDate,endDate) !=null){
            throw new ExistByReservation();
        }
        LocalDate now=LocalDate.now();

        log.info("시작시간"+startDate,"끝난시간"+endDate);

        log.info("현재시간"+now);



        if (now.isBefore(startDate)){
            throw new TimeIsOutReservation();
        }



        Reservation reservation = Reservation.builder()
                        .username(username)
                        .startdate(startDate)
                        .enddate(endDate)
                        .hotelname(reservationReqDto.getHotelname())
                        .roomname(reservationReqDto.getRoomname())
                        .roomcost(reservationReqDto.getRoomcost())
                        .build();


        reservationRepository.save(reservation);


        setHotelMinus(reservationReqDto.getHotelname());
        setRoomMinus(reservationReqDto.getRoomname());

    }

    private void setHotelMinus(String HotelName){
        Hotel hotel = hotelRepository.findByNameContaining(HotelName);
        hotel.setRoomCount(hotel.getRoomCount()-1);
    }
    private void setRoomMinus(String RoomName){


        Room room = roomRepository.findByRoomnameContaining(RoomName);
        room.setCount(room.getCount()-1);
    }

}
