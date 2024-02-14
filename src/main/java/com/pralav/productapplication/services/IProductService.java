package com.pralav.productapplication.services;

import com.pralav.productapplication.dtos.ProductRequestDto;
import com.pralav.productapplication.dtos.ProductResponseDto;
import com.pralav.productapplication.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService
{
    public Product getSingleProduct(Long id);

    public List<Product> getAllProducts();

    public Product addProduct(ProductRequestDto productRequestDto);

    public Product updateProduct(Long id, ProductRequestDto product);
}
