package com.pralav.productapplication.services;

import com.pralav.productapplication.dtos.ProductRequestDto;
import com.pralav.productapplication.dtos.ProductResponseDto;
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
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<ProductResponseDto[]> forObject = restTemplate.getForEntity("https://fakestoreapi.com/products/",
                ProductResponseDto[].class);
        List<ProductResponseDto> productList = Arrays.asList(forObject.getBody());
        List<Product> products = new ArrayList<>();
        for(ProductResponseDto productResponseDto : productList) {
            products.add(getProductFromReesponseDto(productResponseDto));
        }
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
    public Product updateProduct(Long id, ProductRequestDto product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setImage(product.getImage());
        productResponseDto.setCategory(product.getCategory());
        ProductResponseDto productResponseDto1 = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id, productResponseDto, ProductResponseDto.class, id);

        return getProductFromReesponseDto(productResponseDto1);
    }
}
