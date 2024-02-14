package com.pralav.productapplication.controller;

import com.pralav.productapplication.dtos.ProductRequestDto;
import com.pralav.productapplication.models.Product;
import com.pralav.productapplication.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {


    @Qualifier("fakeStoreProductService")
    @Autowired
    private IProductService productService;

    // Get all the products
    @GetMapping("/products/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get single product
    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productService.addProduct(productRequestDto);
    }

    @PatchMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDto productRequestDto) {
        return productService.updateProduct(id,productRequestDto);
    }

    @DeleteMapping("products/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id) {
        return true;
    }
}
