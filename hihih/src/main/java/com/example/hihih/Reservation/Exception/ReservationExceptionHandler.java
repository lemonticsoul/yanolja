package com.example.hihih.Reservation.Exception;


import com.example.hihih.Reservation.Controller.ReservationController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = ReservationController.class)
public class ReservationExceptionHandler {

//    @ExceptionHandler(ExistByReservation.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<ReservationResponse> handler(ExistByReservation e){
//
//
//
////        ReservationResponse response=ReservationResponse.builder()
////                .status(e.getStatus())
////                .message(e.getMessage())
////                .data(e.get)
//
//    }

//    @ExceptionHandler(ReservationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<ReservationResponse> handler(ReservationException e){
//
//
//    }
//
//    @ExceptionHandler(TimeIsOutReservation.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<ReservationResponse> handler(TimeIsOutReservation e){
//
//
//    }

}
