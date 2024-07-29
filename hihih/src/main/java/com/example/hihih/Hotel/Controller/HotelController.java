package com.example.hihih.Hotel.Controller;


import com.example.hihih.Hotel.domain.Hotel;
import com.example.hihih.Hotel.Dto.HotelRequestDto;
import com.example.hihih.Hotel.Service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelService hotelService;


    @GetMapping
    public ResponseEntity<HashMap<String,Object>> getHotelWithRoom(@RequestParam ("page") int page, @RequestParam("limit") int limit) {
        return ResponseEntity.ok(hotelService.getHotelWithRoom(page, limit));
    }


    @PostMapping("/save")
    public ResponseEntity<String> saveHotelWithRoom(@RequestBody List<HotelRequestDto> hotelRequestDto){
            hotelService.saveHotelWithRoom(hotelRequestDto);
            return ResponseEntity.status(201).body("호텔정보가 등록되었습니다,.");
    }

    @GetMapping("/search")
    public ResponseEntity<Hotel> searchHotel(@RequestParam ("name") String name){
        log.info(name);
        return ResponseEntity.status(201).body(hotelService.searchproudct(name));
    }











}
