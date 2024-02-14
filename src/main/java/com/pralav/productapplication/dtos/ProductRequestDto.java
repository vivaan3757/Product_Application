package com.pralav.productapplication.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String title;
    private int price;
    private String description;
    private String image;
    private String category;
}
