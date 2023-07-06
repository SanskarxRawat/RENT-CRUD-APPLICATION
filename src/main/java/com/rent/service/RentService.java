package com.rent.service;


import com.rent.dto.request.RentalBookingRequest;
import com.rent.entity.RentalBooking;

public interface RentService {

    RentalBooking book(RentalBookingRequest rentalBookingRequest);
}
