package com.example.orderservice.mapping;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.model.ShippingAddress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapping {
    /** it will auto ignoer userId as this is not common between shiadd and orderreq */
    ShippingAddress toDTO(OrderRequest orderRequest);
}
