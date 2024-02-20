package com.pralav.productapplication.services;

import com.pralav.productapplication.dtos.ProductRequestDto;
import com.pralav.productapplication.dtos.ProductResponseDto;
import com.pralav.productapplication.exception.InvalidProductIdException;
import com.pralav.productapplication.models.Category;
import com.pralav.productapplication.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


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
    public Product getSingleProduct(Long id) throws InvalidProductIdException {

        if(id >20)
            throw new InvalidProductIdException();
        // I should pass 'id' to fakestore and get the details of this product
        ProductResponseDto forObject = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                ProductResponseDto.class);
        return getProductFromReesponseDto(forObject);
    }

    @Override
    public List<Product> getAllProducts() {
//        // Option 1
//        ResponseEntity<ProductResponseDto[]> forObject = restTemplate.getForEntity("https://fakestoreapi.com/products/",
//                ProductResponseDto[].class);
//        List<ProductResponseDto> productList = Arrays.asList(forObject.getBody());
//        List<Product> products = new ArrayList<>();
//        for(ProductResponseDto productResponseDto : productList) {
//            products.add(getProductFromReesponseDto(productResponseDto));
//        }

        //Option 2
        ProductResponseDto[] productResponseDtos = restTemplate.getForObject("https://fakestoreapi.com/products/",
                ProductResponseDto[].class);
        List<Product> products = new ArrayList<>();
        for(ProductResponseDto prd : productResponseDtos)
            products.add(getProductFromReesponseDto(prd));
        return products;
    }

    @Override
    public Product addProduct(ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseDto = restTemplate.postForObject("https://fakestoreapi.com/products",
                productRequestDto,
                ProductResponseDto.class);
        return getProductFromReesponseDto(productResponseDto);
    }

    @Override
    public Product updateProduct(Long id, ProductRequestDto productRequestDto) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productRequestDto,ProductResponseDto.class);
        HttpMessageConverterExtractor<ProductResponseDto> responseExtractor = new HttpMessageConverterExtractor(ProductResponseDto.class, restTemplate.getMessageConverters());
        ProductResponseDto execute = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return getProductFromReesponseDto(execute);
    }

    @Override
    public Boolean deleteProduct(Long id) throws InvalidProductIdException {
        if(id >20)
            throw new InvalidProductIdException();
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
        return true;
    }
}
