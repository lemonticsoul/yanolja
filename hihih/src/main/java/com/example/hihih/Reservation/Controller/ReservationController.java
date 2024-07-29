package com.example.hihih.Reservation.Controller;


import com.example.hihih.Reservation.Service.ReservationService;
import com.example.hihih.Reservation.dto.ReservationReqDto;
import com.example.hihih.common.SecurityService.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/reservation")
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/add")
    public ResponseEntity<?> addReservation(@AuthenticationPrincipal MemberDetails memberDetails, @RequestBody ReservationReqDto reservationReqDto){

        reservationService.addReservation(memberDetails.getUsername(),reservationReqDto);


        return ResponseEntity.ok("adsfasdf");
    }

}
