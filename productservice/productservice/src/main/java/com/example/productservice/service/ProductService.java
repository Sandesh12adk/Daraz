package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.mapping.ProductRequestToProduct;
import com.example.productservice.mapping.ProductToProductResponse;
import com.example.productservice.model.Product;
import com.example.productservice.repo.BrandRepo;
import com.example.productservice.repo.CategoryrRepo;
import com.example.productservice.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRequestToProduct productRequestToProduct;
    @Autowired
    private ProductToProductResponse productToProductResponse;
    @Autowired
    private BrandRepo brandRepo;
    @Autowired
    private CategoryrRepo categoryrRepo;
    private ProductRepo productRepo;
    @Autowired
    ProductService(ProductRepo productRepo){
        this.productRepo= productRepo;
    }
    public void deleteProduct(int product_id){
        Product product= productRepo.findById(product_id).orElseThrow(
                ()-> {
                    return new ResourceNotFoundException("Cannot find the product with id "+ product_id);
                }
        );
        productRepo.deleteById(product_id);
    }
    public ProductResponse updateProduct(ProductRequest productRequest, int product_id){

        Product productInDb = productRepo.findById(product_id).orElseThrow(() ->
                new ResourceNotFoundException("Cannot find the product with requested id")
        );

        productInDb.setName(productRequest.getName());
        productInDb.setDescription(productRequest.getDescription());
        productInDb.setPrice(productRequest.getPrice());
        productInDb.setStock(productRequest.getStock());
        productInDb.setUserid(productRequest.getUserid());
        productInDb.setCreatedAt(productInDb.getCreatedAt());
        productInDb.setBrand(
                brandRepo.findByname(productRequest.getBrand()).orElseThrow(() ->
                        new ResourceNotFoundException("Cannot find the Brand please select the valid brand")
                )
        );

        productInDb.setCategory(
                categoryrRepo.findByName(productRequest.getCategory()).orElseThrow(() ->
                        new ResourceNotFoundException("Cannot find the category, please choose the valid category")
                )
        );

        return productToProductResponse.convert(productRepo.save(productInDb));
    }
    public List<ProductResponse> getAllProducts(){
        return productRepo.findAll()
                .stream()
                .map(productToProductResponse::convert)
                .toList();
    }
    public ProductResponse getProductById(int id){
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return productToProductResponse.convert(product);
    }
    public List<ProductResponse> getProductsByUser(int userId){
        return productRepo.findByUserid(userId)
                .stream()
                .map(productToProductResponse::convert)
                .toList();
    }
    public List<ProductResponse> getByCategory(String category){
        return productRepo.findByCategory_Name(category)
                .stream()
                .map(productToProductResponse::convert)
                .toList();
    }
    public List<ProductResponse> getByBrand(String brand){
        return productRepo.findByBrand_Name(brand)
                .stream()
                .map(productToProductResponse::convert)
                .toList();
    }
    public List<ProductResponse> searchByName(String name){
        return productRepo.findByNameContainingIgnoreCase(name)
                .stream()
                .map(productToProductResponse::convert)
                .toList();
    }
}
