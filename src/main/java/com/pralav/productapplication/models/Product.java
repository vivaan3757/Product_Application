package com.pralav.productapplication.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private String description;
    private int price;
    private String image;
    private Category category;

}
