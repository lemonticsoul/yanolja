package com.example.hihih.Reservation.Repository;

import com.example.hihih.Reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query("SELECT r FROM Reservation r WHERE r.startdate BETWEEN :startdate AND :enddate")
    Reservation findByReservation(@Param("startdate") LocalDate startDate, @Param("enddate") LocalDate endDate);

}
