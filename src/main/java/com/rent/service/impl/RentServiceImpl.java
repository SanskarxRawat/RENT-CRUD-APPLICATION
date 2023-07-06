package com.rent.service.impl;

import com.rent.dto.request.RentalBookingRequest;
import com.rent.entity.Product;
import com.rent.entity.RentalBooking;
import com.rent.repository.ProductRepository;
import com.rent.repository.RentalBookingRepository;
import com.rent.service.RentService;
import com.rent.util.DurationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;



@Slf4j
@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentalBookingRepository rentalBookingRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public RentalBooking book(RentalBookingRequest rentalBookingRequest) {

        RentalBooking rentalBooking=parseRentalBookingRequest(rentalBookingRequest);
        return rentalBookingRepository.save(rentalBooking);
    }

    private RentalBooking parseRentalBookingRequest(RentalBookingRequest rentalBookingRequest){
        Optional<Product> product=productRepository.findById(rentalBookingRequest.getProductName());
        RentalBooking rentalBooking=new RentalBooking();
        Integer totalHours= DurationUtil.convertToHours(rentalBookingRequest.getDuration(),rentalBookingRequest.getDurationUnit());
        if(product.isPresent()){
            Integer totalCost=product.get().getCostPerHour()*totalHours;
            rentalBooking.setTotalCost(totalCost);
            rentalBooking.setCategory(product.get().getCategory());
        }
        rentalBooking.setDurationInHours(totalHours);
        rentalBooking.setProductName(rentalBookingRequest.getProductName());
        rentalBooking.setStartTime(LocalDateTime.now());
        return rentalBooking;
    }

}