package com.example.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {


    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @Positive(message = "Price must be greater than zero")
    private float price;

    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;

    @NotNull(message = "User ID is required")
    private Integer sellerId; // Using Integer wrapper allows @NotNull to catch null values

    @NotBlank(message = "Category is mandatory")
    private String category;

    @NotBlank(message = "Image URL is mandatory")
    @Pattern(regexp = "^(http|https)://.*$", message = "Must be a valid URL")
    private String imageUrl;

    @NotBlank(message = "Brand is mandatory")
    private String brand;

}
