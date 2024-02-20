package com.pralav.productapplication.services;

import com.pralav.productapplication.dtos.ProductRequestDto;
import com.pralav.productapplication.dtos.ProductResponseDto;
import com.pralav.productapplication.exception.InvalidProductIdException;
import com.pralav.productapplication.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService
{
    public Product getSingleProduct(Long id) throws InvalidProductIdException;

    public List<Product> getAllProducts();

    public Product addProduct(ProductRequestDto productRequestDto);

    public Product updateProduct(Long id, ProductRequestDto product);

    public Boolean deleteProduct(Long id) throws InvalidProductIdException;
}
