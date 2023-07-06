package com.rent.controller;

import com.rent.dto.request.RentalBookingRequest;
import com.rent.entity.RentalBooking;
import com.rent.exception.InvalidProductException;
import com.rent.service.ProductService;
import com.rent.service.RentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/booking")
@Tag(name = "Booking Controller",description = "Controller for managing booking and related operations.")
public class BookingController {


    @Autowired
    private ProductService productService;

    @Autowired
    private RentService rentService;

    @PostMapping("/book")
    @Operation(summary = "This api is used to book product for a specific duration.")
    public ResponseEntity<String> bookProduct(@RequestBody @Valid RentalBookingRequest rentalBookingRequest) throws InvalidProductException {
        log.info("Booking Request: {}",rentalBookingRequest);
        if(!productService.isValidProduct(rentalBookingRequest)){
            throw new InvalidProductException("Invalid Product :"+rentalBookingRequest.getProductName());
        }

        RentalBooking bookedProduct=rentService.book(rentalBookingRequest);

        return ResponseEntity.status(HttpStatus.OK).body("Rental booking created successfully");
    }

}
