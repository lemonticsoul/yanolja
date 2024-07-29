package com.example.hihih.Hotel.Repsitory;

import com.example.hihih.Hotel.domain.Room;

import com.example.hihih.Hotel.Dto.ResponseRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {

    @Query(value = "SELECT r.price, r.roomname FROM Room r WHERE r.trip_id = :tripId",nativeQuery = true)
    List<ResponseRoom> findAllByTripId(@Param("tripId") int tripId);

    Room findByRoomnameContaining(String roomname);
}
