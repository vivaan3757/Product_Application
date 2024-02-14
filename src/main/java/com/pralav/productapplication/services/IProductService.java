package com.pralav.productapplication.services;

import com.pralav.productapplication.models.Product;
import org.springframework.stereotype.Service;

@Service
public interface IProductService
{
    public Product getSingleProduct(Long id);

}
