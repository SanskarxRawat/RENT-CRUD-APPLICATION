package com.rent.dto.request;

import com.rent.constants.RentExceptionConstants;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ProductRequest {

    @NotBlank(message = RentExceptionConstants.EMPTY_FIELD)
    private String name;

    @NotBlank(message = RentExceptionConstants.EMPTY_FIELD)
    private String image;

    @NotNull(message = RentExceptionConstants.EMPTY_FIELD)
    @Positive(message = RentExceptionConstants.INVALID_FIELD)
    private Integer costPerHour;

    @NotBlank(message = RentExceptionConstants.EMPTY_FIELD)
    private String category;
}
