package com.rent.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    private String name;

    private String image;

    private Integer costPerHour;

    private String category;
}
