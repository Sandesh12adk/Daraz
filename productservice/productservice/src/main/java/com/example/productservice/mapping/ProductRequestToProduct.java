package com.example.productservice.mapping;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.model.Image;
import com.example.productservice.model.Product;
import com.example.productservice.repo.BrandRepo;
import com.example.productservice.repo.CategoryrRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class ProductRequestToProduct {
    private BrandRepo brandRepo;
    private CategoryrRepo categoryrRepo;
    @Autowired
    ProductRequestToProduct(BrandRepo brandRepo, CategoryrRepo categoryrRepo){
        this.brandRepo= brandRepo;
        this.categoryrRepo= categoryrRepo;
    }
    public Product convert(ProductRequest productRequest)throws ResourceNotFoundException{
       Product product= new Product();
       product.setName(productRequest.getName());
       product.setDesc(productRequest.getDesc());
       product.setBrand( brandRepo.findByname(productRequest.getBrand()).orElseThrow(()->
                       {
                       return   new ResourceNotFoundException("Cannot find the Brand please select the valid brand");
                       }
       ));
       product.setCategory(categoryrRepo.findByName(productRequest.getName()).orElseThrow(()-> {
                         return  new ResourceNotFoundException("Cannot find the category, please choose the valid category");
                       }));
       Image image= new Image();
       image.setProduct(product); // This will work because hibernet will fist save the produt and get he proeduct
        //referene and save the product here
       image.setUrl(productRequest.getImageUrl());
       image.setCreatedAt(product.getBrand().getCreatedAt()); // This will work because hibernet will fist save the
        //product and get the product time and set the time here
       product.setImageList(List.of(image));

       product.setStock(productRequest.getStock());
       product.setPrice(product.getPrice());
       product.setUserid(1);   // 6200 Later get this from the JwT
        return product;
    }
}
