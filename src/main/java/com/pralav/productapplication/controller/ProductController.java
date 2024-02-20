package com.pralav.productapplication.controller;

import com.pralav.productapplication.dtos.ProductRequestDto;
import com.pralav.productapplication.dtos.ProductWrapper;
import com.pralav.productapplication.exception.InvalidProductIdException;
import com.pralav.productapplication.models.Product;
import com.pralav.productapplication.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductWrapper> getSingleProduct(@PathVariable("id") Long id) {

        ResponseEntity<ProductWrapper> response;
        try {
            Product singleProduct= productService.getSingleProduct(id);
            ProductWrapper productWrapper = new ProductWrapper(singleProduct, "Successfully retrived the data");
            response = new ResponseEntity<>(productWrapper, HttpStatus.OK);
        } catch (InvalidProductIdException e) {
            ProductWrapper productWrapper = new ProductWrapper(null, "Product is not present");
            response = new ResponseEntity<>(productWrapper,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productService.addProduct(productRequestDto);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDto productRequestDto) {
        return productService.updateProduct(id,productRequestDto);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") Long id) {
        ResponseEntity<Boolean> response;
        try {
            boolean bool = productService.deleteProduct(id);
            response = new ResponseEntity<>(bool, HttpStatus.OK);
        } catch (InvalidProductIdException e) {
            response = new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
