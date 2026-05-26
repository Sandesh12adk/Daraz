package com.example.productservice.mapping;

import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Product;
import com.example.productservice.repo.BrandRepo;
import com.example.productservice.repo.CategoryrRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductResponse {
    @Autowired
    private BrandRepo brandRepo;
    @Autowired
    private CategoryrRepo categoryrRepo;
    public ProductResponse convert(Product product){
        ProductResponse productResponse= new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setBrand(product.getBrand().getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setCategory(product.getCategory().getName());
        productResponse.setStock(product.getStock());
        productResponse.setUserid(product.getSellerId());
        productResponse.setImageUrl(product.getImageList().get(0).getUrl());
        return productResponse;
    }
}
