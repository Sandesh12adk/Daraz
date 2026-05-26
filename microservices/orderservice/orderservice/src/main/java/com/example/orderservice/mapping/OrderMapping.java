package com.example.orderservice.mapping;

import com.example.orderservice.constant.OrderStatus;
import com.example.orderservice.dto.CartResponse;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.model.ShippingAddress;
import com.example.orderservice.service.CartFeignClient;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapping {
    @Autowired
    private AddressMapping addressMapping;
    @Autowired
    private CartFeignClient cartFeignClient;
   public Order orderRequestToOrder(OrderRequest orderRequest){
       Order order= new Order();


       order.setOrderStatus(OrderStatus.CONFIRMED);

       ShippingAddress shippingAddress= addressMapping.toDTO(orderRequest);
       order.setShippingAddress(shippingAddress);

       int userId= orderRequest.getUserId();  //6200 we cannot turst the user id from request jwt is needed
       order.setUserid(userId);

      CartResponse cartResponse= cartFeignClient.getCartByUserId(userId).getBody();
       List<OrderItem> orderItems = cartResponse.getCartItems()
               .stream()
               .map(item -> {
                   OrderItem orderItem = new OrderItem();
                   orderItem.setProductId(item.getProductId());
                   orderItem.setProductNameSnapshot(item.getName());
                   orderItem.setQuantity(item.getProductQuantity());
                   orderItem.setUnitPriceSnapshot(item.getProductPriceSnapshot());
                   orderItem.setOrder(order);
                   return orderItem;
               })
               .toList();
       order.setOrderItemList(orderItems);
       return order;
   }
    public OrderResponse toOrderResponse(Order order) {

        OrderResponse response = new OrderResponse();

        response.setOrderId(order.getUniqueId());
        response.setUserId(order.getUserid());
        response.setOrderStatus(order.getOrderStatus());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());

        // ---------------- SHIPPING ADDRESS ----------------
        if (order.getShippingAddress() != null) {
            OrderResponse.ShippingAddressDTO addressDTO =
                    new OrderResponse.ShippingAddressDTO();

            addressDTO.setCountry(order.getShippingAddress().getCountry());
            addressDTO.setProvince(order.getShippingAddress().getProvince());
            addressDTO.setDistrict(order.getShippingAddress().getDistrict());
            addressDTO.setCity(order.getShippingAddress().getCity());
            addressDTO.setStreet(order.getShippingAddress().getStreet());

            response.setShippingAddress(addressDTO);
        }

        // ---------------- ORDER ITEMS ----------------
        List<OrderResponse.OrderItemDTO> itemDTOs =
                order.getOrderItemList()
                        .stream()
                        .map(item -> {

                            OrderResponse.OrderItemDTO dto =
                                    new OrderResponse.OrderItemDTO();

                            dto.setProductId(item.getProductId());
                            dto.setProductNameSnapshot(item.getProductNameSnapshot());
                            dto.setQuantity(item.getQuantity());
                            dto.setUnitPriceSnapshot(item.getUnitPriceSnapshot());

                            // calculated field
                            dto.setTotalPrice(
                                    item.getQuantity() * item.getUnitPriceSnapshot()
                            );

                            return dto;
                        })
                        .toList();

        response.setOrderItems(itemDTOs);

        // ---------------- TOTAL AMOUNT ----------------
        float totalAmount = (float) order.getOrderItemList()
                .stream()
                .mapToDouble(i ->
                        i.getQuantity() * i.getUnitPriceSnapshot()
                )
                .sum();

        response.setTotalAmount(totalAmount);

        return response;
    }
}
