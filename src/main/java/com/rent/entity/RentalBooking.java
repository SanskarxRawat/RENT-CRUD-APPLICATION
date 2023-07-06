package com.rent.entity;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booking")
public class RentalBooking {

    @Id
    private String productName;

    private LocalDateTime startTime;

    private Integer durationInHours;

    private Integer totalCost;

    private String category;

}
