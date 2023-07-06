package com.rent.controller;

import com.rent.dto.request.ProductRequest;
import com.rent.entity.Product;
import com.rent.enums.DurationUnit;
import com.rent.exception.ProductNotAvailableException;
import com.rent.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/product")
@Tag(name = "Product Controller",description = "Controller for managing product and related operations.")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{category}/{duration}/{durationUnit}")
    @Operation(summary = "This api is used to get list of all available products.")
    public ResponseEntity<List<Product>> getAvailableProducts(@PathVariable("category") String category, @PathVariable("duration") Integer duration, @PathVariable("durationUnit") DurationUnit durationUnit) throws ProductNotAvailableException {
        log.info("Category : {}, Duration : {} and it's unit : {}",category,duration,durationUnit);
        List<Product> availableProducts=productService.getAvailableProducts(category,duration,durationUnit);
        log.info("List of Available Products : {}",availableProducts);
        if(availableProducts.isEmpty()){
            throw new ProductNotAvailableException("No Products are available for given category and duration.");
        }

        return ResponseEntity.ok(availableProducts);
    }

    @PostMapping("/save")
    @Operation(summary = "This api is used to save the product")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductRequest productRequest){
        Product savedProduct=productService.saveProduct(productRequest);
        log.info("Product Saved : {}",savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
}
