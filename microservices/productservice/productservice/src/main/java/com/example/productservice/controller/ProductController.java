package com.example.productservice.controller;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import com.example.productservice.mapping.ProductRequestToProduct;
import com.example.productservice.mapping.ProductToProductResponse;
import com.example.productservice.model.Product;
import com.example.productservice.repo.ProductRepo;
import com.example.productservice.service.KafkaService;
import com.example.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private  final ProductRepo productrepo;
    @Autowired
    private ProductRequestToProduct productRequestToProduct;
    @Autowired
    private ProductToProductResponse productToProductResponse;
    @Autowired
    private ProductService productService;
    @Autowired
    private KafkaService kafkaService;
    @Autowired
    public ProductController(ProductRepo productrepo){
        this.productrepo= productrepo;
    }
    @PostMapping("/add")
    public ResponseEntity<ProductResponse> saveProduct(@Valid @RequestBody  ProductRequest productRequest){
       Product product= productrepo.save(productRequestToProduct.convert(productRequest));
       ProductResponse productResponse = productToProductResponse.convert(product);
       kafkaService.sendProductCreatedEvent(product.getId());
               return  ResponseEntity.ok(productResponse);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable int id){
    ProductResponse productResponse= productService.updateProduct(productRequest, id);
    return ResponseEntity.ok(productResponse);
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable int id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductResponse>> getByUser(@PathVariable int userId){
        return ResponseEntity.ok(productService.getProductsByUser(userId));
    }
    @GetMapping("/category/{name}")
    public ResponseEntity<List<ProductResponse>> getByCategory(@PathVariable String name){
        return ResponseEntity.ok(productService.getByCategory(name));
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> search(@RequestParam String name){
        return ResponseEntity.ok(productService.searchByName(name));
    }
    /*
POST /product/add              -> add single product
DELETE /product/delete/{id}
PUT /update/{id}            -> update the product
GET /product                  → all products
GET /product/{id}            → single product
GET /product/user/{id}       → seller products
GET /product/category/{name} → filter category
GET /product/brand/{name}    → filter brand
GET /product/search?name=x   → search*/
}
