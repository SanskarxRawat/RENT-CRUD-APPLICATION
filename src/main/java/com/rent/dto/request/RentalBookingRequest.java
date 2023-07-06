package com.rent.dto.request;

import com.rent.constants.RentExceptionConstants;
import com.rent.enums.DurationUnit;
import lombok.Data;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
public class RentalBookingRequest {

    @NotBlank(message = RentExceptionConstants.EMPTY_FIELD)
    private String productName;

    @NotNull(message = RentExceptionConstants.EMPTY_FIELD)
    @Positive(message = RentExceptionConstants.INVALID_FIELD)
    private Integer duration;

    @NotNull(message = RentExceptionConstants.EMPTY_FIELD)
    private DurationUnit durationUnit;
}
