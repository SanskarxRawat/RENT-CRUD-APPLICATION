package com.rent.service;

import com.rent.dto.request.ProductRequest;
import com.rent.dto.request.RentalBookingRequest;
import com.rent.entity.Product;
import com.rent.enums.DurationUnit;

import java.util.List;

public interface ProductService {

    List<Product> getAvailableProducts(String category, Integer duration, DurationUnit durationUnit);

    Boolean isValidProduct(RentalBookingRequest rentalBookingRequest);

    Product saveProduct(ProductRequest productRequest);
}
