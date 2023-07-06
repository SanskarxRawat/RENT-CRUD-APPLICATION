package com.rent.service.impl;

import com.rent.dto.request.ProductRequest;
import com.rent.dto.request.RentalBookingRequest;
import com.rent.entity.Product;
import com.rent.entity.RentalBooking;
import com.rent.enums.DurationUnit;
import com.rent.repository.ProductRepository;
import com.rent.repository.RentalBookingRepository;
import com.rent.service.ProductService;
import com.rent.util.DurationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RentalBookingRepository rentalBookingRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAvailableProducts(String category, Integer duration, DurationUnit durationUnit) {
        List<Product> allProducts=productRepository.findAllByCategory(category);
        Integer totalHours= DurationUtil.convertToHours(duration,durationUnit);
        log.info("All Products with given category: {}",allProducts);

        List<Product> availableProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (isProductAvailable(category,product, totalHours)) {
                availableProducts.add(product);
            }
        }

        return availableProducts;
    }

    @Override
    public Boolean isValidProduct(RentalBookingRequest rentalBookingRequest) {
        Product product=productRepository.findById(rentalBookingRequest.getProductName()).orElse(null);
        if(Objects.nonNull(product)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Product saveProduct(ProductRequest productRequest) {
        Product product=parseProductRequest(productRequest);
        return productRepository.save(product);
    }

    private Product parseProductRequest(ProductRequest productRequest){
        Product product=new Product();
        product.setName(productRequest.getName());
        product.setImage(productRequest.getImage());
        product.setCategory(productRequest.getCategory());
        product.setCostPerHour(productRequest.getCostPerHour());
        return product;
    }


    private Boolean isProductAvailable(String category,Product product, Integer totalHours) {
       List<RentalBooking> bookedProduct=rentalBookingRepository.findAllByProductName(product.getName());
       if(bookedProduct.isEmpty()){
           return Boolean.TRUE;
       }
       List<RentalBooking> bookings=rentalBookingRepository.findAllByCategory(category);

        for (RentalBooking booking : bookings) {

            if (isBookingOverlapping(booking, totalHours)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private Boolean isBookingOverlapping(RentalBooking booking, Integer duration) {
        LocalDateTime bookingStartTime = booking.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusHours(booking.getDurationInHours());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime currentBookingEndTime = now.plusHours(duration);

        return bookingStartTime.isBefore(now) && bookingEndTime.isAfter(now) ||
                bookingStartTime.isBefore(currentBookingEndTime) && bookingEndTime.isAfter(currentBookingEndTime);
    }

}
