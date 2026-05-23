package com.example.cartservce.mapping;

import com.example.cartservce.constants.CartStatus;
import com.example.cartservce.dto.CartResponse;
import com.example.cartservce.dto.NewCart;
import com.example.cartservce.dto.ProductResponse;
import com.example.cartservce.dto.RequestCart;
import com.example.cartservce.model.Cart;
import com.example.cartservce.model.CartItem;
import com.example.cartservce.service.CartService;
import com.example.cartservce.service.InventoryFeignclient;
import com.example.cartservce.service.ProductFeignClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CartMapper {

    NewCart convertCartToNewCart(Cart cart);
    @Mapping(target = "cartStatus", constant = "ACTIVE")
    Cart convertRequestCartToCart(RequestCart requestCart);
    @Mapping(target = "cartItems", source = "cartItemList")
    CartResponse convertCartToCartResponse(Cart cart);
}
