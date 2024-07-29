package com.example.hihih.Hotel.Service;


import com.example.hihih.Hotel.Repsitory.RoomRepository;
import com.example.hihih.Hotel.Repsitory.HotelRepository;
import com.example.hihih.Hotel.domain.Room;
import com.example.hihih.Hotel.domain.Hotel;
import com.example.hihih.Hotel.Dto.FinalResponseDto;
import com.example.hihih.Hotel.Dto.ResponseRoom;
import com.example.hihih.Hotel.Dto.HotelRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
@RequiredArgsConstructor
public class HotelService {


    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public void saveHotelWithRoom(List<HotelRequestDto> hotelRequestDto) {
        for (HotelRequestDto hotelRequestdto: hotelRequestDto){
            Hotel hotel =Hotel.builder().name(hotelRequestdto.getName()).price(hotelRequestdto.getPrice()).build();
            hotelRepository.save(hotel);
            for (Room room :hotelRequestdto.getRoom()){
                Room new_room =Room.builder().roomname(room.getRoomname()).price(room.getPrice()).hotel(hotel).build();
                roomRepository.save(new_room);
            }
        }
    }
    public HashMap<String,Object> getHotelWithRoom(int page, int limit){
        List<FinalResponseDto> FinalResponsedtos=new ArrayList<>();

        Pageable pageable = PageRequest.of(page-1, limit, Sort.by("id"));
        Page<Hotel> hotels = hotelRepository.findAll(pageable);
        List<Hotel> finalhotels= hotels.getContent();

        for (Hotel finalhotel:finalhotels){
            List<ResponseRoom> rooms=roomRepository.findAllByTripId(finalhotel.getId());


            FinalResponseDto finalResponseDto = FinalResponseDto.builder()
                    .id(finalhotel.getId())
                    .price(finalhotel.getPrice())
                    .name(finalhotel.getName())
                    .roomList(rooms)
                    .build();

            FinalResponsedtos.add(finalResponseDto);
        }

        HashMap<String,Object> response=new HashMap<>();
        response.put("totalpage",hotels.getTotalPages());
        response.put("hotels",FinalResponsedtos);

        return response;


    }

    public void deleteHotelAndRoomAll(){
        hotelRepository.deleteAll();
        roomRepository.deleteAll();
    }

    public void deleteHotelAndRoomOne(){

    }

    public Hotel searchproudct(String name){

        return hotelRepository.findByNameContaining(name);
    }


}
