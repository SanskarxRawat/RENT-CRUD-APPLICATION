package com.rent.repository;

import com.rent.entity.RentalBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalBookingRepository extends JpaRepository<RentalBooking,String> {

    List<RentalBooking> findAllByCategory(String category);

    List<RentalBooking> findAllByProductName(String productName);
}
