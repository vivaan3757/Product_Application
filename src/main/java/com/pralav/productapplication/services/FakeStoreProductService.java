package com.pralav.productapplication.services;

import com.pralav.productapplication.dtos.ProductResponseDto;
import com.pralav.productapplication.models.Category;
import com.pralav.productapplication.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements IProductService{

    @Autowired
    RestTemplate restTemplate;
    public Product getProductFromReesponseDto(ProductResponseDto productResponseDto) {
        Product product = new Product();
        product.setId(productResponseDto.getId());
        product.setName(productResponseDto.getTitle());
        product.setPrice(productResponseDto.getPrice());
        product.setDescription(productResponseDto.getDescription());
        Category category = new Category();
        category.setName(productResponseDto.getCategory());
        product.setCategory(category);
        return product;
    }
    @Override
    public Product getSingleProduct(Long id) {

        // I should pass 'id' to fakestore and get the details of this product
        ProductResponseDto forObject = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                ProductResponseDto.class);
        return getProductFromReesponseDto(forObject);
    }
}
