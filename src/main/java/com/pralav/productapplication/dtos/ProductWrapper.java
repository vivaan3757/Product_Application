package com.pralav.productapplication.dtos;

import com.pralav.productapplication.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductWrapper {

    public ProductWrapper(Product product, String message) {
        this.product = product;
        this.message = message;
    }

    private Product product;
    private String message;


}
