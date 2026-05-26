package com.example.orderservice.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class ShippingAddress {
private String country;
private String province;
private String district;
private String city;
private String street;
}
