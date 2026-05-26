package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.mapping.OrderMapping;
import com.example.orderservice.model.Order;
import com.example.orderservice.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderMapping orderMapping;
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order= orderMapping.orderRequestToOrder(orderRequest);
        Order savedOrder= orderRepo.save(order);
        OrderResponse orderResponse= orderMapping.toOrderResponse(savedOrder);
        return orderResponse;
    }
}
