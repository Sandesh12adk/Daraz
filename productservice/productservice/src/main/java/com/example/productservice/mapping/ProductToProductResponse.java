package com.example.productservice.mapping;

import com.example.productservice.dto.ProductResponse;
import com.example.productservice.model.Product;

public class ProductToProductResponse {
    public ProductResponse convert(Product product){
        ProductResponse productResponse= new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDesc(product.getDesc());
        productResponse.setBrand(productResponse.getBrand());
        productResponse.setPrice(productResponse.getPrice());
        productResponse.setCategory(productResponse.getCategory());
        productResponse.setStock(product.getStock());
        productResponse.setUserid(product.getUserid());
        productResponse.setImageUrl(product.getImageList().get(0).getUrl());
        return productResponse;
    }
}
