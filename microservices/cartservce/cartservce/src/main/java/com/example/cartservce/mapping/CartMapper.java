package com.example.cartservce.mapping;

import com.example.cartservce.dto.CartResponse;
import com.example.cartservce.dto.NewCart;
import com.example.cartservce.dto.RequestCart;
import com.example.cartservce.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface CartMapper {

    NewCart convertCartToNewCart(Cart cart);
    @Mapping(target = "cartStatus", constant = "ACTIVE")
    Cart convertRequestCartToCart(RequestCart requestCart);
    @Mapping(target = "cartItems", source = "cartItemList")
    CartResponse convertCartToCartResponse(Cart cart);
}
