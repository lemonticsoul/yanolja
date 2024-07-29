package com.example.hihih.Hotel.Repsitory;

import com.example.hihih.Hotel.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;




public interface HotelRepository extends JpaRepository<Hotel,Long>{

    Hotel findByNameContaining(String name);


}
